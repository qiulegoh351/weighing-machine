package com.farmtech.weighingmachine.ui.components.material

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.material3.TextButton as MaterialTextButton

/**
 * ===========================
 * TextButton
 * ===========================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    disableRippleEffect: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    colors: ButtonColors = ButtonDefaults.textButtonColors(
        containerColor = Color.Red
    ),
    rippleColor: Color? = null,
    rippleAlpha: RippleAlpha? = null,
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    // ======================== VARIABLES
    val rippleConfiguration =
        RippleConfiguration(
            color = rippleColor ?: ColorTheme.primary,
            rippleAlpha ?: DefaultRippleAlpha
        )
    val rippleTheme = if (disableRippleEffect) null else rippleConfiguration


    // ========================= VIEW
    CompositionLocalProvider(
        LocalRippleConfiguration provides rippleTheme
    ) {
        MaterialTextButton(
            onClick,
            modifier = modifier.wrapContentHeight(),
            enabled,
            shape,
            colors,
            elevation,
            border,
            contentPadding,
            interactionSource,
            content
        )
    }
}