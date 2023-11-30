package com.example.ui.views.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

const val SHORT_ANIMATION_DURATION = 200L

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.visibleOrNot(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun View.disable() {
    if (isEnabled) isEnabled = false else return
}

fun View.enable() {
    if (isEnabled) return else isEnabled = true
}

fun View.shortFadeIn() {
    this.apply {
        alpha = 0f
        visible()
        animate()
            .alpha(1f)
            .setDuration(SHORT_ANIMATION_DURATION)
            .setListener(null)
    }
}

fun View.shortFadeOut() {
    this.animate()
        .alpha(0f)
        .setDuration(SHORT_ANIMATION_DURATION)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                this@shortFadeOut.gone()
            }
        })
}