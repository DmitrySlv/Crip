package com.dscreate_app.crip.presentation

import android.app.Application
import androidx.work.Configuration
import com.dscreate_app.crip.data.database.AppDatabase
import com.dscreate_app.crip.data.mapper.CoinMapper
import com.dscreate_app.crip.data.network.ApiFactory
import com.dscreate_app.crip.data.workers.RefreshDataWorkerFactory
import com.dscreate_app.crip.di.DaggerApplicationComponent

class CoinApp: Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).dao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
}