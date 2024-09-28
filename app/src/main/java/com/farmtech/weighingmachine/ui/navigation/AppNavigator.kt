package com.farmtech.weighingmachine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import com.farmtech.weighingmachine.AuthRepository
import com.farmtech.weighingmachine.ui.navigation.navigationGraph.appGraph
import com.farmtech.weighingmachine.ui.navigation.navigationGraph.authGraph

@Composable
fun AppNavigator(appNavController: NavController) {
    val isAuth by AuthRepository.isAuthenticated.collectAsState()
    val startDestination = if (isAuth) NAVIGATION_PATH.COLLECT_SETTING.route else NAVIGATION_PATH.SIGN_IN.route

    NavHost(
        navController = appNavController.navController,
        startDestination
    ) {
        authGraph(appNavController)
        appGraph(appNavController)
    }
}