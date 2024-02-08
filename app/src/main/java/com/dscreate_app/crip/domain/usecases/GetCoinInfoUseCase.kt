package com.dscreate_app.crip.domain.usecases

import com.dscreate_app.crip.domain.CoinRepository
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor (
   private val repository: CoinRepository
) {
     operator fun invoke(fSym: String) = repository.getCoinInfo(fSym)
}