package com.dscreate_app.crip.domain.usecases

import com.dscreate_app.crip.domain.CoinRepository

class LoadDataUseCase(
   private val repository: CoinRepository
) {
    suspend operator fun invoke() = repository.loadData()
}