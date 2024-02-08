package com.dscreate_app.crip.presentation

import android.app.Application
import com.dscreate_app.crip.di.DaggerApplicationComponent

class CoinApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}