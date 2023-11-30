package com.example.ui.views.delegates

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

@Suppress("SpellCheckingInspection")
interface ShowSnackbarDelegate {
    fun showShortSnackbar(view: View, @StringRes resId: Int)
}
@Suppress("SpellCheckingInspection")
class ShowSnackbarDelegateImpl : ShowSnackbarDelegate {

    override fun showShortSnackbar(view: View, @StringRes resId: Int) {
        Snackbar.make(view, resId, Snackbar.LENGTH_SHORT).show()
    }
}