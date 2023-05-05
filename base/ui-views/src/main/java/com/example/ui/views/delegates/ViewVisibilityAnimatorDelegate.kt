package com.example.ui.views.delegates

import android.view.View
import com.example.ui.views.extensions.shortFadeIn
import com.example.ui.views.extensions.shortFadeOut

interface ViewVisibilityAnimatorDelegate {
    fun shortFadeIn(vararg views: View)
    fun shortFadeOut(vararg views: View)
}

class ViewVisibilityAnimatorDelegateImpl : ViewVisibilityAnimatorDelegate {

    override fun shortFadeIn(vararg views: View) {
        views.forEach { it.shortFadeIn() }
    }

    override fun shortFadeOut(vararg views: View) {
        views.forEach { it.shortFadeOut() }
    }
}