package com.example.feb.utils.transformers

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs


class CubeInTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.cameraDistance = 20000f
        when {
            position < -1 -> {
                page.alpha = 0f
            }
            position <= 0 -> {
                page.alpha = 1f
                page.setPivotX(page.width.toFloat())
                page.rotationY = 90 * abs(position)
            }
            position <= 1 -> {
                page.alpha = 1f
                page.pivotX = 0f
                page.rotationY = -90 * abs(position)
            }
            else -> {
                page.setAlpha(0f)
            }
        }
        if (abs(position) <= 0.5) {
            page.scaleY = .4f.coerceAtLeast(1 - Math.abs(position))
        } else if (Math.abs(position) <= 1) {
            page.setScaleY(Math.max(.4f, 1 - Math.abs(position)))
        }
    }
}
