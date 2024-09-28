package com.farmtech.weighingmachine.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.R
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreDetailButton(onClick: () -> Unit) {
    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        Row(
            modifier = Modifier.clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(0.5.dp)
        ) {
            Text(
                "More Details",
                color = ColorTheme.primary,
                style = TypographyTheme.titleSmall
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.chevron_right),
                contentDescription = "More Detail"
            )
        }
    }
}