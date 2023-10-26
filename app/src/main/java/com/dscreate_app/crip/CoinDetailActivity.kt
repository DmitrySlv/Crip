package com.dscreate_app.crip

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dscreate_app.crip.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinDetailBinding.inflate(layoutInflater) }
    private val viewModel: CoinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        fromSymbol?.let {
            viewModel.getDetailInfo(fromSymbol).observe(this) {
               binding.apply {
                   tvPrice.text = it.price
                   tvMinPrice.text = it.lowDay.toString()
                   tvMaxPrice.text = it.highDay.toString()
                   tvLastMarket.text = it.lastMarket
                   tvLastUpdate.text = it.getFormattedTime()
                   tvFromSymbol.text = it.fromSymbol
                   tvToSymbol.text = it.toSymbol
                   Picasso.get().load(it.getFullImageUrl()).into(imLogoCoin)
               }
            }
        }
    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}