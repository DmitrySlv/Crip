package com.dscreate_app.crip

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dscreate_app.crip.databinding.ActivityCoinPriceListBinding
import io.reactivex.disposables.CompositeDisposable

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinPriceListBinding.inflate(layoutInflater) }
    private val compositeDisposable = CompositeDisposable()
    private val viewModel: CoinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.priceList.observe(this) {
            Log.d("MyLog", "Success in activity: $it")
        }
        viewModel.getDetailInfo("BTC").observe(this) {
            Log.d("MyLog", "Success in activity: $it")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}