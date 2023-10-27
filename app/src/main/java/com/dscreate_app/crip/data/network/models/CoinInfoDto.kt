package com.dscreate_app.crip.data.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dscreate_app.crip.data.network.ApiFactory.BASE_IMAGE_URL
import com.dscreate_app.crip.presentation.utils.convertTime
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
) {
    fun getFormattedTime(): String {
        return convertTime(lastUpdate.toLong())
    }

    fun getFullImageUrl(): String {
       return BASE_IMAGE_URL + imageUrl
    }
}
