package com.farmtech.weighingmachine.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import androidx.compose.material3.FilterChip as MaterialFilterChip

@Composable
fun FilterChip(
    text: String,
    selected: Boolean,
    onChange: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    cornerRadius: Int = 8
) {
    val color = if (selected) Color.White else ColorTheme.textPrimary
    val chipShape = RoundedCornerShape(cornerRadius.dp)
    MaterialFilterChip(
        onClick = { onChange() },
        label = {
            Box(
                modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(text, textAlign = TextAlign.Center, color = color)
            }
        },
        selected = selected,
        enabled = enabled,
        modifier = modifier.height(60.dp),
        shape = chipShape,
        border = BorderStroke(0.dp, Color.Unspecified),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color(0xffdcf0e1),
            selectedContainerColor = ColorTheme.primary,
        )
    )
}