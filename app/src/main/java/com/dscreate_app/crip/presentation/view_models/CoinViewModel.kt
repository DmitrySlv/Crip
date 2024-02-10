package com.dscreate_app.crip.presentation.view_models

import androidx.lifecycle.ViewModel
import com.dscreate_app.crip.domain.usecases.GetCoinInfoListUseCase
import com.dscreate_app.crip.domain.usecases.GetCoinInfoUseCase
import com.dscreate_app.crip.domain.usecases.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
): ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
            loadDataUseCase()
    }
}