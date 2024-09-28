package com.farmtech.weighingmachine.ui.components.utils

import android.graphics.Color;
import com.farmtech.weighingmachine.MainActivity
import com.farmtech.weighingmachine.R
import io.github.muddz.styleabletoast.StyleableToast


object Toast {
    fun errorToast(
        message: String? = "",
    ) {
        StyleableToast.Builder(MainActivity.appContext)
            .text(message)
            .textColor(Color.WHITE)
            .backgroundColor(Color.parseColor("#FB5858"))
            .iconStart(R.drawable.toast_error)
            .stroke(10, Color.TRANSPARENT)
            .cornerRadius(6)
            .show()
    }

    fun successToast(
        message: String? = "",
    ) {
        StyleableToast.Builder(MainActivity.appContext)
            .text(message)
            .textColor(Color.WHITE)
            .backgroundColor(Color.parseColor("#47CB18"))
            .iconStart(R.drawable.toast_success)
            .stroke(10, Color.TRANSPARENT)
            .cornerRadius(6)
            .show()
    }
}
