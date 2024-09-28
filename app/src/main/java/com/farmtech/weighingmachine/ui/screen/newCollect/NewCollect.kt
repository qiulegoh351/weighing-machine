package com.farmtech.weighingmachine.ui.screen.newCollect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.farmtech.weighingmachine.model.collects
import com.farmtech.weighingmachine.ui.components.NewCollectBottomBar
import com.farmtech.weighingmachine.ui.components.NewCollectList
import com.farmtech.weighingmachine.ui.components.Scaffold
import com.farmtech.weighingmachine.ui.navigation.NavController


@Composable
fun NewCollect(appNavController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        modifier,
        bottomBar = { NewCollectBottomBar(weight = "66.66") }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NewCollectList(collects)
        }
    }
}