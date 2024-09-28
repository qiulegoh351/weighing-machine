package com.farmtech.weighingmachine.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.farmtech.weighingmachine.model.CropGroup
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewCollectList(collects: List<CropGroup>) {
    val lazyListState = rememberLazyListState()
    var selectedCrop by remember { mutableStateOf<Int?>(null) }

    fun onChange(value: Int) {
        selectedCrop = value
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        itemsIndexed(collects) { index, item ->
            Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = item.cropName,
                    style = TypographyTheme.titleLarge,
                )
                BoxWithConstraints {
                    val itemWidth = (maxWidth / 7) - 7.dp // Adjust spacing to match FlowRow spacing
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        maxItemsInEachRow = 7,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item.cropItems.forEach { crop ->
                            FilterChip(
                                text = crop.cropItemName,
                                selected = selectedCrop == crop.cropItemId,
                                onChange = { onChange(crop.cropItemId)},
                                modifier = Modifier.width(itemWidth)
                            )
                        }
                    }
                }
            }

            if (index < collects.size - 1) {
                HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(top = 35.dp))
            }
        }
    }
}
