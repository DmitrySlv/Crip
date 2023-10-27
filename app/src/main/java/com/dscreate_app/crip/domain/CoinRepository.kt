package com.dscreate_app.crip.domain

import androidx.lifecycle.LiveData

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfoEntity>>

    fun getCoinInfo(fSym: String): LiveData<CoinInfoEntity>
}