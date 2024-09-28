package com.farmtech.weighingmachine.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@Composable
fun PreviewField(label: String, value: String = "") {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            label, color = ColorTheme.textSecondary, style = TypographyTheme.titleSmall.copy(
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.15.sp
            )
        )
        Text(value)
    }
}