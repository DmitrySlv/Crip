package com.dscreate_app.crip.di

import android.app.Application
import com.dscreate_app.crip.data.database.AppDatabase
import com.dscreate_app.crip.data.database.CoinDao
import com.dscreate_app.crip.data.database.repository.CoinRepositoryImpl
import com.dscreate_app.crip.data.network.ApiFactory
import com.dscreate_app.crip.data.network.ApiService
import com.dscreate_app.crip.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(
            application: Application
        ): CoinDao {
            return AppDatabase.getInstance(application).dao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}