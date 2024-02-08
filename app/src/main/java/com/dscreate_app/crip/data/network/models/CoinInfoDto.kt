package com.dscreate_app.crip.data.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfoDto(
    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String,

    @SerializedName("PRICE")
    @Expose
    val price: String,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Int,

    @SerializedName("HIGHDAY")
    @Expose
    val highDay: Double,

    @SerializedName("LOWDAY")
    @Expose
    val lowDay: Double,

    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String,

    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String
)
