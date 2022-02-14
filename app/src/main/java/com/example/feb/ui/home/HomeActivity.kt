package com.example.feb.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewpager.widget.ViewPager
import com.example.feb.databinding.ActivityHomeBinding
import com.example.feb.ui.home.logs.QuoteLogsContainer
import com.example.feb.ui.home.quote.Quotes
import com.example.feb.utils.transformers.CubeInTransformer
import com.example.feb.utils.transformers.DepthPageTransformer
import com.example.feb.utils.transformers.ZoomOutTransformer

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    private val homePagerAdapter by lazy {
        HomePagerAdapter(
            fragmentManager = supportFragmentManager,
            lifecycle =  lifecycle,
            fragments = listOf(Quotes.getInstance(), QuoteLogsContainer.getInstance())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeActivityFullScreen()

        setUpViewPager()
    }

    private fun makeActivityFullScreen() {
        val windowInsetsController = ViewCompat.getWindowInsetsController(binding.root) ?: return
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    private fun setUpViewPager() {
        binding.container.adapter = homePagerAdapter
        binding.container.setPageTransformer(DepthPageTransformer())
    }
}