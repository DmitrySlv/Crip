package com.dscreate_app.crip.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dscreate_app.crip.data.network.ApiFactory.BASE_IMAGE_URL
import com.dscreate_app.crip.presentation.utils.convertTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfo(
    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String? = null,

    @SerializedName("PRICE")
    @Expose
    val price: String? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Int? = null,

    @SerializedName("HIGHDAY")
    @Expose
    val highDay: Double? = null,

    @SerializedName("LOWDAY")
    @Expose
    val lowDay: Double? = null,

    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String? = null
) {
    fun getFormattedTime(): String {
        return convertTime(lastUpdate?.toLong())
    }

    fun getFullImageUrl(): String {
       return BASE_IMAGE_URL + imageUrl
    }
}
