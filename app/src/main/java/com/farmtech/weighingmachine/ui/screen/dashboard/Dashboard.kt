package com.farmtech.weighingmachine.ui.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items as columnItems
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.model.Crop
import com.farmtech.weighingmachine.model.cropItems
import com.farmtech.weighingmachine.model.statistics
import com.farmtech.weighingmachine.ui.components.CropItem
import com.farmtech.weighingmachine.ui.components.Scaffold
import com.farmtech.weighingmachine.ui.components.StatisticCard
import com.farmtech.weighingmachine.ui.components.material.PullToRefreshBox
import com.farmtech.weighingmachine.ui.components.utils.isEmpty
import com.farmtech.weighingmachine.ui.components.utils.logMessage
import com.farmtech.weighingmachine.ui.components.screen.dashboard.DashboardFilterBar
import com.farmtech.weighingmachine.ui.components.screen.dashboard.StatisticSideBar
import com.farmtech.weighingmachine.ui.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class FilterType {
    DAY, MONTH
}

data class Filter(
    val type: FilterType = FilterType.DAY,
    val startDate: Long? = null,
    val endDate: Long? = null,
    val crop_id: Number? = null
)

val defaultFilter = Filter(
    type = FilterType.MONTH,
    startDate = null,
    endDate = null
)

/**
 * ===========================
 * DASHBOARD SCREEN
 * ===========================
 */
@ExperimentalMaterial3Api
@Composable
fun Dashboard(appNavController: NavController, modifier: Modifier = Modifier) {
    // ========================= STATES
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            // fetch something
            delay(500)
            isRefreshing = false
        }
    }
    var filterValue by remember { mutableStateOf<Filter>(defaultFilter) }

    // ========================= EVENTS
    val onFilterChange: (Filter) -> Unit = {
        filterValue = it
    }

    val onHandleCropClick: (data: Crop, action: String) -> Unit = { data, action ->
        when (action) {
            "delete" -> {
                filterValue = filterValue.copy(crop_id = null)
                logMessage("delete action: $filterValue")
            }

            else -> logMessage(data.cropItemName)
        }
    }

    logMessage("${isEmpty(filterValue.crop_id)} here")

    // ========================= VIEW
    Scaffold(
        topBar = { DashboardFilterBar(filterValue, onFilterChange) },
    ) {
        Box(
            modifier = Modifier.padding(it),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                    )
            ) {
                //  =============== LEFT SIDE BAR
                StatisticSideBar(modifier = Modifier.weight(.32f))

                //  =============== MAIN LISTING
                PullToRefreshBox(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh,
                ) {
                    //  =============== STATISTIC LISTING
                    if (!isEmpty(filterValue.crop_id)) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(bottom = 16.dp)
                        ) {
                            columnItems(cropItems) { item ->
                                CropItem(data = item, onClick = onHandleCropClick)
                            }
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(bottom = 30.dp, top = 2.dp)
                        ) {
                            items(statistics) { item ->
                                StatisticCard(
                                    item,
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = { it -> onFilterChange(filterValue.copy(crop_id = it)) })
                            }
                        }
                    }
                }
            }
        }
    }
}