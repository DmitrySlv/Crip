package com.dscreate_app.crip.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dscreate_app.crip.R
import com.dscreate_app.crip.databinding.ItemCoinInfoBinding
import com.dscreate_app.crip.domain.CoinInfoEntity
import com.squareup.picasso.Picasso

class CoinInfoAdapter: ListAdapter<CoinInfoEntity, CoinInfoAdapter.ViewHolder>(DiffCoinPrice) {

    var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onClickListener?.let { holder.setData(getItem(position), it) }
    }

    class ViewHolder(
        private val binding: ItemCoinInfoBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun setData(coinPrice: CoinInfoEntity, onCoinClickListener: OnClickListener) = with(binding) {
            coinPrice.apply {
                val symbolsTemplate = root.context.getString(R.string.symbols_template)
                val lastUpdateTemplate = root.context.getString(R.string.last_update_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = price
                tvLastUpdate.text = String.format(lastUpdateTemplate, lastUpdate)
                Picasso.get().load(imageUrl).into(imLogoCoin)

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