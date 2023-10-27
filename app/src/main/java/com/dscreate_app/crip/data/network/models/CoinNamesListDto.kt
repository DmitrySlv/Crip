package com.dscreate_app.crip.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNamesListDto(
    @SerializedName("Data")
    @Expose
    val coinsNames: List<CoinNameContainerDto>? = null
)
