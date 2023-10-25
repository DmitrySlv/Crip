package com.dscreate_app.crip.models

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceInfoRawData(
    @SerializedName("RAW")
    @Expose
    val jsonObject: JsonObject? = null
)
