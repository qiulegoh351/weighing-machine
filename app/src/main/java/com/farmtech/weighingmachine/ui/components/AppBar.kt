package com.farmtech.weighingmachine.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.farmtech.weighingmachine.R
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.components.utils.logMessage
import com.farmtech.weighingmachine.ui.navigation.NAVIGATION_PATH
import com.farmtech.weighingmachine.ui.navigation.NavController
import com.farmtech.weighingmachine.ui.navigation.useGetCurrentRoute
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@Composable
fun AppBar(
    appNavController: NavController,
    modifier: Modifier = Modifier,
) {
    val routeInfo = useGetCurrentRoute(navController = appNavController)
    val currentRoute = routeInfo.route
    val routeTitle = routeInfo.title

    fun getTintColor(route: String): Color {
        return if (currentRoute == route) Color(0xff024e2a) else Color.Unspecified
    }

    logMessage("Current Route: $routeTitle")

    Box(
        modifier = modifier
            .height(80.dp)
            .background(ColorTheme.primary)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                IconButton(onClick = { appNavController.navigateToAppBarRoute(NAVIGATION_PATH.PROFILE.route) }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.app_logo),
                    contentDescription = "Logo",
                    tint = Color.White
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(
                    onClick = { appNavController.navigateToAppBarRoute(NAVIGATION_PATH.HOME.route) },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.analysis),
                        contentDescription = "Analysis",
                        tint = getTintColor(NAVIGATION_PATH.HOME.route)
                    )
                }
                IconButton(
                    onClick = { appNavController.navigateToAppBarRoute(NAVIGATION_PATH.COLLECT_SETTING.route) },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.collects),
                        contentDescription = "Collect",
                        tint = getTintColor(NAVIGATION_PATH.COLLECT_SETTING.route)
                    )
                }
                IconButton(
                    onClick = { appNavController.navigateToAppBarRoute(NAVIGATION_PATH.NEW_COLLECT.route) },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.create_collect),
                        contentDescription = "Create Collect",
                        tint = getTintColor(NAVIGATION_PATH.NEW_COLLECT.route)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(2f)
                .align(Alignment.Center)
        ) {
            Text(
                routeTitle,
                maxLines = 1,
                style = TypographyTheme.titleMedium,
                color = Color.White,
                textAlign = TextAlign.Center, // Ensure text is centered within the Box
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
