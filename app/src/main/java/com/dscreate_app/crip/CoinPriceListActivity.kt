package com.dscreate_app.crip

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscreate_app.crip.adapters.CoinInfoAdapter
import com.dscreate_app.crip.databinding.ActivityCoinPriceListBinding
import com.dscreate_app.crip.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinPriceListBinding.inflate(layoutInflater) }
    private val viewModel: CoinViewModel by viewModels()
    private lateinit var adapter: CoinInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        adapter = CoinInfoAdapter()
        adapter.onClickListener = object : CoinInfoAdapter.OnClickListener {
            override fun onClick(coinPrice: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity, coinPrice.fromSymbol
                )
                startActivity(intent)
            }
        }
        rvCoinPriceList.layoutManager = LinearLayoutManager(this@CoinPriceListActivity)
        rvCoinPriceList.adapter = adapter
        viewModel.priceList.observe(this@CoinPriceListActivity) {
           adapter.submitList(it)
        }

    }
}