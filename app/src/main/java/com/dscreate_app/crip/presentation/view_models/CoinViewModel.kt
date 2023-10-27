package com.dscreate_app.crip.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dscreate_app.crip.data.network.ApiFactory
import com.dscreate_app.crip.data.database.AppDatabase
import com.dscreate_app.crip.data.models.CoinPriceInfo
import com.dscreate_app.crip.data.models.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(
   application: Application
): AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.dao().getPriceList()

    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return db.dao().getPriceInfoAboutCoin(fSym)
    }

    init {
        loadData()
    }

   private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 50)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe( {
                db.dao().insertPriceList(it)
            }, {
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceInfo> {
        val result = mutableListOf<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.jsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}