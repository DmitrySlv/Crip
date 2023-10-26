package com.dscreate_app.crip

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.dscreate_app.crip.api.ApiFactory
import com.dscreate_app.crip.database.AppDatabase
import com.dscreate_app.crip.pojo.CoinPriceInfo
import com.dscreate_app.crip.pojo.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinViewModel(
   application: Application
): AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    val priceList = db.dao().getPriceList()
    private val compositeDisposable = CompositeDisposable()

    fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 50)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRawData(it) }

            .subscribeOn(Schedulers.io())
            .subscribe( {
                db.dao().insertPriceList(it)
                Log.d("MyLog", "Success: $it")
            }, {
                Log.d("MyLog", "Failure: ${it.message}")
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