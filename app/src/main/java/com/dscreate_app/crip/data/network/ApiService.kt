package com.dscreate_app.crip.data.network

import com.dscreate_app.crip.data.network.models.CoinNamesListDto
import com.dscreate_app.crip.data.network.models.CoinInfoJsonContainerDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(API_KEY) apiKey: String = API_KEY_DEF,
        @Query(QUERY_PARAM_LIMIT) limit: Int = LIMIT,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(API_KEY) apiKey: String = API_KEY_DEF,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String
    ): CoinInfoJsonContainerDto

    companion object {
        private const val API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
        private const val LIMIT = 10
        private const val API_KEY_DEF =
            "eed784ad69c723bd936a008d50468d15198eb31ff0309adc822af39084baa96c"
    }
}