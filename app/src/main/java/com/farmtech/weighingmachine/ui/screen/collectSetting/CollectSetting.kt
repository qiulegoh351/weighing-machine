package com.farmtech.weighingmachine.ui.screen.collectSetting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.ui.components.Scaffold
import com.farmtech.weighingmachine.ui.components.screen.cropSetting.CropSettingBottomBar
import com.farmtech.weighingmachine.ui.components.screen.cropSetting.CropSettingCard
import com.farmtech.weighingmachine.ui.navigation.NavController

@Composable
fun CollectSetting(appNavController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        modifier,
        bottomBar = { CropSettingBottomBar() }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 24.dp, horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    CropSettingCard(
                        label = "1. Product",
                        category = "Selection",
                        isRequired = true,
                        onEdit = {},
                        onDelete = {})
                }

                item {
                    CropSettingCard(
                        label = "2. Grade",
                        category = "Selection",
                        isRequired = true,
                        onEdit = {},
                        onDelete = {})
                }
            }
        }
    }
}