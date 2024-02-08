package com.dscreate_app.crip.domain.usecases

import com.dscreate_app.crip.domain.CoinRepository
import javax.inject.Inject

class GetCoinInfoListUseCase @Inject constructor (
   private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}