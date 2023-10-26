package com.dscreate_app.crip.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dscreate_app.crip.pojo.CoinPriceInfo

@Dao
interface CoinDao {

    @Query("SELECT * FROM full_price_list ORDER BY lastUpdate")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    @Query("SELECT * FROM full_price_list WHERE fromSymbol IS :fSym LIMIT 1")
    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfo>)
}