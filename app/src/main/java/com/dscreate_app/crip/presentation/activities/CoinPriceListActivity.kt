package com.dscreate_app.crip.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscreate_app.crip.R
import com.dscreate_app.crip.databinding.ActivityCoinPriceListBinding
import com.dscreate_app.crip.domain.CoinInfoEntity
import com.dscreate_app.crip.presentation.CoinApp
import com.dscreate_app.crip.presentation.adapters.CoinInfoAdapter
import com.dscreate_app.crip.presentation.fragments.CoinDetailFragment
import com.dscreate_app.crip.presentation.view_models.CoinViewModel
import com.dscreate_app.crip.presentation.view_models.ViewModelFactory
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinPriceListBinding.inflate(layoutInflater) }
    private lateinit var viewModel: CoinViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
       val adapter = CoinInfoAdapter()
        rvCoinPriceList.layoutManager = LinearLayoutManager(this@CoinPriceListActivity)
        rvCoinPriceList.adapter = adapter
        rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(
            this@CoinPriceListActivity, viewModelFactory
        )[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this@CoinPriceListActivity) {
           adapter.submitList(it)
        }
        adapter.onClickListener = object : CoinInfoAdapter.OnClickListener {
            override fun onClick(coinPrice: CoinInfoEntity) {
                if (isOnePaneMode()) {
                    launchDetailActivity(coinPrice.fromSymbol)
                } else {
                    launchDetailFragment(coinPrice.fromSymbol)
                }
            }
        }
    }

    private fun launchDetailFragment(fSym: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CoinDetailFragment.newInstance(fSym))
            .addToBackStack(null)
            .commit()
    }

    private fun launchDetailActivity(fSym: String) {
        val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity,fSym)
        startActivity(intent)
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null
}