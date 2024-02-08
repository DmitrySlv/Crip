package com.dscreate_app.crip.domain.usecases

import com.dscreate_app.crip.domain.CoinRepository

class GetCoinInfoUseCase(
   private val repository: CoinRepository
) {
     operator fun invoke(fSym: String) = repository.getCoinInfo(fSym)
}