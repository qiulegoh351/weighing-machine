package com.farmtech.weighingmachine.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.farmtech.weighingmachine.AuthRepository
import com.farmtech.weighingmachine.ui.components.AppBar
import com.farmtech.weighingmachine.ui.navigation.AppNavigator
import com.farmtech.weighingmachine.ui.navigation.rememberNavController
import com.farmtech.weighingmachine.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        val appNavController = rememberNavController()
        val isAuth by AuthRepository.isAuthenticated.collectAsState()

        Scaffold(
            topBar = {
                if (isAuth) {
                    AppBar(
                        appNavController = appNavController,

                    )
                }
            },
//            modifier = Modifier.statusBarsPadding()
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AppNavigator(appNavController = appNavController)
            }
        }
    }
}