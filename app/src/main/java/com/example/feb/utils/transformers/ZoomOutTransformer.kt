package com.example.feb.utils.transformers

import android.view.View
import androidx.viewpager2.widget.ViewPager2

private const val MIN_SCALE = 0.85f

class ZoomOutTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 1 -> {
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))

                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    alpha = 1f
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}