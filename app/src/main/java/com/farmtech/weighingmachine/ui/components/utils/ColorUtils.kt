package com.farmtech.weighingmachine.ui.components.utils

import androidx.compose.ui.graphics.Color
import android.graphics.Color as GraphicsColor


fun hexToColor(hexaCode: String?):Color {
    if (isEmpty(hexaCode)) {
        return Color.Unspecified
    }
    return Color(GraphicsColor.parseColor(hexaCode))
}