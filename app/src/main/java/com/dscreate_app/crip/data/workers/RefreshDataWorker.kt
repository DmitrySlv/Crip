package com.dscreate_app.crip.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.dscreate_app.crip.data.database.CoinDao
import com.dscreate_app.crip.data.mapper.CoinMapper
import com.dscreate_app.crip.data.network.ApiService
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinDao: CoinDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
): CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListDtoToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListDto(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
                Log.d("MyLog", e.message.toString())
            }
            delay(10000)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"

        fun workRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}