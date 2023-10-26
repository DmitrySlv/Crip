package com.dscreate_app.crip.adapters

import androidx.recyclerview.widget.DiffUtil
import com.dscreate_app.crip.pojo.CoinPriceInfo

object DiffCoinPrice: DiffUtil.ItemCallback<CoinPriceInfo>() {

    override fun areItemsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem == newItem
    }
}