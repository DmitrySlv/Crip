package com.dscreate_app.crip.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.dscreate_app.crip.data.database.CoinDao
import com.dscreate_app.crip.data.mapper.CoinMapper
import com.dscreate_app.crip.data.network.ApiService
import javax.inject.Inject

class RefreshDataWorkerFactory @Inject constructor(
    private val coinDao: CoinDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
): WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return RefreshDataWorker(
            appContext,
            workerParameters,
            coinDao,
            apiService,
            mapper
        )
    }

}