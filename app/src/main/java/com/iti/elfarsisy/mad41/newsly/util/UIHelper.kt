package com.iti.elfarsisy.mad41.newsly.util

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.iti.elfarsisy.mad41.newsly.R

object UIHelper {

    fun showError(error: String, view: View) {
        Snackbar.make(view, error, Snackbar.LENGTH_LONG)
            .setAction("CLOSE") { }
            .setBackgroundTint(view.resources.getColor(R.color.design_default_color_error))
            .setActionTextColor(view.resources.getColor(R.color.white))
            .show()
    }
}