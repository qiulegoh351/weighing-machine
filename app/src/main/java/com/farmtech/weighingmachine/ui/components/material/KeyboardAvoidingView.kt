package com.farmtech.weighingmachine.ui.components.material

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.farmtech.weighingmachine.ui.components.utils.rememberImeState

@Composable
fun KeyboardAvoidingView(
    modifier: Modifier = Modifier,
    alwaysScrollToEndOfBottom: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
) {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    val isKeyboardOpen = imeState.value
    val scrollToEndOfBottom = isKeyboardOpen && alwaysScrollToEndOfBottom

    LaunchedEffect(scrollToEndOfBottom) {
        if (scrollToEndOfBottom) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    Column(
        modifier = modifier
            .verticalScroll(scrollState),
        verticalArrangement,
        horizontalAlignment,
        content
    )
}
