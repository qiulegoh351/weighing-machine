/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.farmtech.weighingmachine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

data class RouteInfo(
    val route: String?,
    val title: String
)


/**
 * Destinations used in the [App].
 */
object ROUTE {
    const val SIGNIN = "signin"
    const val SIGNUP = "signup"
    const val DASHBOARD = "home"
    const val NEW_COLLECT = "new-collect"
    const val COLLECT_DETAIL = "colleact-detail"
    const val COLLECT_SETTING = "crop-setting"
    const val PROFILE = "profile"
}

enum class NAVIGATION_PATH(
    val title: String,
    val route: String,
    val icon: ImageVector?=null
) {
    SIGN_IN("Sign In", ROUTE.SIGNIN),
    SIGN_UP("Sign Up", ROUTE.SIGNUP),
    HOME("Dashboard", ROUTE.DASHBOARD),
    COLLECT_DETAIL("Collect Detail", ROUTE.COLLECT_DETAIL),
    NEW_COLLECT("New Collect", ROUTE.NEW_COLLECT),
    COLLECT_SETTING("Collect Setting", ROUTE.COLLECT_SETTING),
    PROFILE("Profile", ROUTE.PROFILE),
}

/**
 * Remembers and creates an instance of [NavController]
 */
@Composable
fun rememberNavController(
    navController: NavHostController = rememberNavController()
): NavController = remember(navController) {
    NavController(navController)
}

/**
 * Responsible for holding UI Navigation logic.
 */
@Stable
class NavController(
    val navController: NavHostController,
) {

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------
    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToAppBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                // Pop up backstack to the first destination and save state. This makes going back
                // to the start destination when pressing back in any other bottom tab.
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

}

@Composable
fun useGetCurrentRoute(navController: NavController):RouteInfo {
    val navBackStackEntry by navController.navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val routeTitle = NAVIGATION_PATH.entries.find { it.route == currentRoute }?.title ?: "Unknown"

    return RouteInfo(route = currentRoute, title = routeTitle)
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

/**
 * Copied from similar function in NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
