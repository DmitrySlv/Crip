package com.dscreate_app.crip.data.mapper

import com.dscreate_app.crip.data.database.CoinInfoDbModel
import com.dscreate_app.crip.data.network.models.CoinInfoDto
import com.dscreate_app.crip.data.network.models.CoinInfoJsonContainerDto
import com.dscreate_app.crip.data.network.models.CoinNamesListDto
import com.dscreate_app.crip.domain.CoinInfoEntity
import com.google.gson.Gson

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto) = CoinInfoDbModel(
        fromSymbol = dto.fromSymbol,
        toSymbol = dto.toSymbol,
        price = dto.price,
        lastUpdate = dto.lastUpdate,
        highDay = dto.lastUpdate.toDouble(),
        lowDay = dto.lowDay,
        lastMarket = dto.lastMarket,
        imageUrl = dto.imageUrl
    )

    fun mapJsonContainerToListDto(json: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = json.coinJson ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListDtoToString(namesListDto: CoinNamesListDto): String {
       return namesListDto.coinsNames?.map { it.coinNameDto?.name }?.joinToString(",") ?: ""
    }

    fun mapDbModelToCoinEntity(dbModel: CoinInfoDbModel) = CoinInfoEntity(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lastUpdate = dbModel.lastUpdate,
        highDay = dbModel.lastUpdate.toDouble(),
        lowDay = dbModel.lowDay,
        lastMarket = dbModel.lastMarket,
        imageUrl = dbModel.imageUrl
    )
}