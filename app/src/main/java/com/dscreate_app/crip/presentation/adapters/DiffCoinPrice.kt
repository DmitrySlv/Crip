package com.dscreate_app.crip.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.dscreate_app.crip.data.network.models.CoinInfoDto
import com.dscreate_app.crip.domain.CoinInfoEntity

object DiffCoinPrice: DiffUtil.ItemCallback<CoinInfoEntity>() {

    override fun areItemsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem == newItem
    }
}