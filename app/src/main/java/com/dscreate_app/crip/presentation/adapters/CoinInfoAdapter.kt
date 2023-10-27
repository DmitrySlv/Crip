package com.dscreate_app.crip.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dscreate_app.crip.R
import com.dscreate_app.crip.data.network.ApiFactory
import com.dscreate_app.crip.databinding.ItemCoinInfoBinding
import com.dscreate_app.crip.data.network.models.CoinInfoDto
import com.dscreate_app.crip.domain.CoinInfoEntity
import com.dscreate_app.crip.presentation.utils.convertTime
import com.squareup.picasso.Picasso

class CoinInfoAdapter: ListAdapter<CoinInfoEntity, CoinInfoAdapter.ViewHolder>(DiffCoinPrice) {

    var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onClickListener?.let { holder.setData(getItem(position), it) }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
       private val binding = ItemCoinInfoBinding.bind(view)

        fun setData(coinPrice: CoinInfoEntity, onCoinClickListener: OnClickListener) = with(binding) {
            coinPrice.apply {
                val symbolsTemplate = root.context.getString(R.string.symbols_template)
                val lastUpdateTemplate = root.context.getString(R.string.last_update_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = price
                tvLastUpdate.text = String.format(lastUpdateTemplate, convertTime(lastUpdate.toLong()))
                Picasso.get().load(ApiFactory.BASE_IMAGE_URL + imageUrl).into(imLogoCoin)

                itemView.setOnClickListener {
                    onCoinClickListener.onClick(coinPrice)
                }
            }
        }
    }

    interface OnClickListener {
        fun onClick(coinPrice: CoinInfoEntity)
    }
}