package com.farmtech.weighingmachine.ui.components.material

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Button as MaterialButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.ui.components.utils.isEmpty
import com.farmtech.weighingmachine.ui.theme.ColorTheme

enum class ButtonColorVariant(
    val containerColor: Color,
    val contentColor: Color,
) {
    PRIMARY(ColorTheme.primaryButton, Color.White),
    SECONDARY(ColorTheme.primary100, ColorTheme.textPrimary)
}

val DefaultRippleAlpha = RippleAlpha(0.5f, 0.5f, 0.5f, 0.5f)

/**
 * ===========================
 * Button
 * ===========================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    loadingTitle: String = "Loading",
    enabled: Boolean = true,
    disableRippleEffect: Boolean = true,
    shape: Shape = RoundedCornerShape(8.dp),
    opacity: Float = 0.85f,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    rippleColor: Color? = null,
    rippleAlpha: RippleAlpha? = null,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colorVariant: ButtonColorVariant? = ButtonColorVariant.PRIMARY,
    content: @Composable RowScope.() -> Unit,
) {
    val isPressed by interactionSource.collectIsPressedAsState()

    // ======================== VARIABLES
    val buttonColors = colors.copy(
        disabledContainerColor =colorVariant?.containerColor ?: colors.containerColor,
        containerColor = colorVariant?.containerColor ?: colors.containerColor,
        contentColor = colorVariant?.contentColor ?: colors.contentColor,
        disabledContentColor = colorVariant?.contentColor ?: colors.contentColor,

    )
    val rippleConfiguration =
        RippleConfiguration(
            color = rippleColor ?: ColorTheme.primary,
            rippleAlpha ?: DefaultRippleAlpha
        )
    val rippleTheme = if (disableRippleEffect) null else rippleConfiguration
    val disabledButton = !enabled || loading

    if (isPressed){
        // DisposableEffect to wait for the press action is completed
        DisposableEffect(Unit) {
            onDispose {
                // ============= IS RELEASED
                // logMessage("Release")
            }
        }
    }

    // ========================= VIEW
    CompositionLocalProvider(LocalRippleConfiguration provides rippleTheme) {
        MaterialButton(
            onClick,
            modifier = modifier.indication(interactionSource, null)
                .graphicsLayer {
                    // Change opacity based on press state
                    alpha = when {
                        disabledButton -> 0.5f // if button is disabled | loading
                        isPressed -> opacity // opacity when pressed
                        else -> 1f // Default to fully opaque
                    }
                },
            enabled = !disabledButton,
            shape,
            colors = buttonColors,
            elevation,
            border,
            contentPadding,
            interactionSource,
        ) {
            if (loading) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = LocalContentColor.current,
                        strokeWidth = 2.dp,
                    )
                    if (!isEmpty(loadingTitle)) {
                        Text(text = loadingTitle, color = Color.White)
                    }
                }

            } else {
                content()
            }
        }
    }
}