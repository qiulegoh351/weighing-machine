package com.farmtech.weighingmachine.ui.components.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.ui.components.FilterChip
import com.farmtech.weighingmachine.ui.components.input.DateInput
import com.farmtech.weighingmachine.ui.screen.dashboard.Filter
import com.farmtech.weighingmachine.ui.screen.dashboard.FilterType


@Composable
fun DashboardFilterBar(
    filterValue: Filter,
    onFilterChange: (value: Filter) -> Unit
) {
    val filterTypeModifier = Modifier
        .widthIn(min = 112.dp)
        .height(48.dp)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        FilterChip(
            text = "Today",
            selected = filterValue.type === FilterType.DAY,
            onChange = { onFilterChange(filterValue.copy(type = FilterType.DAY)) },
            modifier = filterTypeModifier
        )
        FilterChip(
            text = "This Month",
            selected = filterValue.type === FilterType.MONTH,
            onChange = { onFilterChange(filterValue.copy(type = FilterType.MONTH)) },
            modifier = filterTypeModifier
        )
        DateInput(
            onDateSelected = {
                onFilterChange(filterValue.copy(startDate = it))
            },
        )
    }
}