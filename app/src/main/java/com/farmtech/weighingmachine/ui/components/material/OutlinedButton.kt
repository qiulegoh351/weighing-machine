package com.farmtech.weighingmachine.ui.components.material

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import androidx.compose.material3.OutlinedButton as MaterialOutlinedButton


private val DefaultOutlinedRippleAlpha = RippleAlpha(0.1f, 0.1f, 0.1f, 0.1f)


/**
 * ===========================
 * Outlined Button
 * ===========================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    disableRippleEffect: Boolean = false,
    shape: Shape = RoundedCornerShape(8.dp),
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        contentColor = ColorTheme.textPrimary
    ),
    rippleColor: Color? = null,
    rippleAlpha: RippleAlpha? = null,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    borderColor: Color = ColorTheme.primaryButton, // Add borderColor parameter
    border: BorderStroke? = null, // Update default value to null
    contentPadding: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    // ======================== VARIABLES
    val rippleConfiguration =
        RippleConfiguration(
            color = rippleColor ?: ColorTheme.primary100,
            rippleAlpha ?: DefaultOutlinedRippleAlpha
        )
    val rippleTheme = if (disableRippleEffect) null else rippleConfiguration

    // Create a BorderStroke with the specified borderColor if border is not provided
    val finalBorder = border ?: BorderStroke(1.dp, borderColor)

    // ========================= VIEW
    CompositionLocalProvider(LocalRippleConfiguration provides rippleTheme) {
        MaterialOutlinedButton(
            onClick,
            modifier,
            enabled,
            shape,
            colors,
            elevation,
            border = finalBorder,
            contentPadding,
            interactionSource,
            content,
        )
    }
}
