package com.dscreate_app.crip.di

import android.app.Application
import com.dscreate_app.crip.data.database.AppDatabase
import com.dscreate_app.crip.data.database.CoinDao
import com.dscreate_app.crip.data.database.repository.CoinRepositoryImpl
import com.dscreate_app.crip.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinDao {
            return AppDatabase.getInstance(application).dao()
        }
    }
}