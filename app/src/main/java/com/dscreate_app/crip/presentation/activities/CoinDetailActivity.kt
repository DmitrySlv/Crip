package com.dscreate_app.crip.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dscreate_app.crip.R
import com.dscreate_app.crip.databinding.ActivityCoinDetailBinding
import com.dscreate_app.crip.presentation.fragments.CoinDetailFragment

class CoinDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CoinDetailFragment.newInstance(fromSymbol))
                .commit()
        }
    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}