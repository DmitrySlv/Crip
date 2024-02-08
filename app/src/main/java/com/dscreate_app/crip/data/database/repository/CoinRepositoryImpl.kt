package com.dscreate_app.crip.data.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.dscreate_app.crip.data.database.AppDatabase
import com.dscreate_app.crip.data.database.CoinDao
import com.dscreate_app.crip.data.mapper.CoinMapper
import com.dscreate_app.crip.data.workers.RefreshDataWorker
import com.dscreate_app.crip.domain.CoinInfoEntity
import com.dscreate_app.crip.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor (
   private val application: Application,
   private val dao: CoinDao,
   private val mapper: CoinMapper
): CoinRepository {

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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.workRequest()
        )
    }
}