package com.farmtech.weighingmachine.ui.navigation.navigationGraph

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.farmtech.weighingmachine.ui.navigation.NAVIGATION_PATH
import com.farmtech.weighingmachine.ui.navigation.NavController
import com.farmtech.weighingmachine.ui.screen.auth.signin.SignIn
import com.farmtech.weighingmachine.ui.screen.auth.signup.SignUp
import com.farmtech.weighingmachine.ui.screen.collectSetting.CollectSetting
import com.farmtech.weighingmachine.ui.screen.dashboard.CollectDetail
import com.farmtech.weighingmachine.ui.screen.dashboard.Dashboard
import com.farmtech.weighingmachine.ui.screen.newCollect.NewCollect
import com.farmtech.weighingmachine.ui.screen.profile.Profile

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.appGraph(appNavController: NavController) {
    composable(NAVIGATION_PATH.HOME.route) { from ->
        Dashboard(appNavController)
    }
    composable(NAVIGATION_PATH.NEW_COLLECT.route) { from ->
        NewCollect(appNavController)
    }
    composable(NAVIGATION_PATH.COLLECT_SETTING.route) { from ->
        CollectSetting(appNavController)
    }
    composable(NAVIGATION_PATH.COLLECT_DETAIL.route) { from ->
        CollectDetail(appNavController)
    }
    composable(NAVIGATION_PATH.PROFILE.route) { from ->
        Profile(appNavController)
    }
}

fun NavGraphBuilder.authGraph(appNavController: NavController) {
    composable(NAVIGATION_PATH.SIGN_IN.route) { from ->
        SignIn(appNavController)
    }
    composable(NAVIGATION_PATH.SIGN_UP.route) { from ->
        SignUp(appNavController)
    }
}