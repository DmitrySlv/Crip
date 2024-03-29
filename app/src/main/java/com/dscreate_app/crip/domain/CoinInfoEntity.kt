package com.dscreate_app.crip.domain

data class CoinInfoEntity(
    val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val lastUpdate: String,
    val highDay: Double,
    val lowDay: Double,
    val lastMarket: String,
    val imageUrl: String
)
