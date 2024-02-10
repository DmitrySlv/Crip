package com.dscreate_app.crip.di

import android.app.Application
import com.dscreate_app.crip.presentation.CoinApp
import com.dscreate_app.crip.presentation.activities.CoinPriceListActivity
import com.dscreate_app.crip.presentation.fragments.CoinDetailFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}