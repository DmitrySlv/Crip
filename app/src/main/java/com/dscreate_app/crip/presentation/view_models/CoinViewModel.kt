package com.dscreate_app.crip.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dscreate_app.crip.data.database.repository.CoinRepositoryImpl
import com.dscreate_app.crip.domain.usecases.GetCoinInfoListUseCase
import com.dscreate_app.crip.domain.usecases.GetCoinInfoUseCase
import com.dscreate_app.crip.domain.usecases.LoadDataUseCase

class CoinViewModel(
   application: Application
): AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
            loadDataUseCase()
    }
}