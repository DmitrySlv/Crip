package com.dscreate_app.crip.data.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dscreate_app.crip.data.database.AppDatabase
import com.dscreate_app.crip.data.mapper.CoinMapper
import com.dscreate_app.crip.data.network.ApiFactory
import com.dscreate_app.crip.domain.CoinInfoEntity
import com.dscreate_app.crip.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    application: Application
): CoinRepository {

    private val dao = AppDatabase.getDatabase(application).dao()
    private val mapper = CoinMapper()
    private val apiService = ApiFactory.apiService

    override fun getCoinInfoList(): LiveData<List<CoinInfoEntity>> {
        return dao.getPriceList().map {
            it.map {
                mapper.mapDbModelToCoinEntity(it)
            }
        }
    }

    override fun getCoinInfo(fSym: String): LiveData<CoinInfoEntity> {
        return dao.getPriceInfoAboutCoin(fSym).map {
            mapper.mapDbModelToCoinEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListDtoToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListDto(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                dao.insertPriceList(dbModelList)
                delay(10000)
            } catch (e: Exception) {
            }
        }
    }
}