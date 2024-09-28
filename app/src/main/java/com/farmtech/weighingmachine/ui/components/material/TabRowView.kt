package com.farmtech.weighingmachine.ui.components.material

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)


@Composable
fun TabRowView(
    tabItem: List<TabItem>
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabItem.size }

    LaunchedEffect(selectedTab) {
        pagerState.animateScrollToPage(selectedTab)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTab = pagerState.currentPage
    }

    TabRow(selectedTabIndex = selectedTab) {
        tabItem.forEachIndexed { index, item ->
            Tab(selected = index == selectedTab, onClick = {
                selectedTab = index
            },
                text = { Text(text = item.title) },
                icon = {
                    Icon(
                        imageVector = if (index == selectedTab) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = item.title
                    )
                }
            )
        }
    }

    HorizontalPager(
        state = pagerState
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

        }
    }
}
