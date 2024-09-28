package com.farmtech.weighingmachine.ui.components.material

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox as MaterialPullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.farmtech.weighingmachine.ui.theme.ColorTheme

/**
 * ===========================
 * PULL TO REFRESH
 * ===========================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshBox(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit
) {
    // ========================= STATES
    val state = remember {
        object : PullToRefreshState {
            private val anim = Animatable(0f, Float.VectorConverter)

            override val distanceFraction
                get() = anim.value

            override suspend fun animateToThreshold() {
//                anim.animateTo(0f, spring(dampingRatio = Spring.DampingRatioHighBouncy))
            }

            override suspend fun animateToHidden() {
                anim.animateTo(0f)
            }

            override suspend fun snapTo(targetValue: Float) {
                anim.snapTo(targetValue)
            }
        }
    }

    // ========================= VIEW
    MaterialPullToRefreshBox(
        modifier = modifier,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        contentAlignment = contentAlignment,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                state = state,
                color = ColorTheme.primary,
                containerColor = Color.White
            )
        },
        state = state,
        content = content
    )
}