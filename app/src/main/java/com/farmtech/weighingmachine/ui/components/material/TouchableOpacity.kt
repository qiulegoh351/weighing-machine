package com.farmtech.weighingmachine.ui.components.material

import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

/**
 * ===========================
 * TouchableOpacity BUTTON
 * ===========================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouchableOpacity(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    opacity: Float = 0.85f,
    enabled: Boolean = true,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

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
    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        Box(
            modifier = modifier
                .indication(interactionSource, null)
                .graphicsLayer {
                    // Change opacity based on press state
                    alpha = if (isPressed) opacity else 1f
                }
                .clickable(
                    onClick = onClick,
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = null
                ),
            contentAlignment = contentAlignment,
            propagateMinConstraints = propagateMinConstraints,
            content = content
        )
    }
}