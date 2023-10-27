package com.dscreate_app.crip.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dscreate_app.crip.data.network.ApiFactory.BASE_IMAGE_URL
import com.dscreate_app.crip.presentation.utils.convertTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val lastUpdate: Int,
    val highDay: Double,
    val lowDay: Double,
    val lastMarket: String,
    val imageUrl: String
)
