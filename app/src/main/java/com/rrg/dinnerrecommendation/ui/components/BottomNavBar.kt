package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.get
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.models.keys.BottomBarScreens

@Composable
fun BottomNavBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavBarMainScreens: MutableState<List<BottomBarScreens>> = remember {
        mutableStateOf(BottomBarScreens.values().toList())
    }

    val currentMainBottomNavRoute: MutableState<String> = remember {
        mutableStateOf(BottomBarScreens.Recommendation.route)
    }

    BottomNavigation(
        elevation = 4.dp
    ) {
        bottomNavBarMainScreens.value.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route || screen.childrenRoutes.contains(it.route) } == true,
                onClick = {
                    if (screen.route != currentMainBottomNavRoute.value) {
                        // Save last route of Bottom Nav Screen
                        navController.currentDestination?.route?.let { lastRouteBeforeSwitchingBottomNavScreen ->
                            bottomNavBarMainScreens.value.find { it.route == currentMainBottomNavRoute.value }?.lastVisitedRoute = lastRouteBeforeSwitchingBottomNavScreen
                        }

                        // Navigate to last route of New Bottom Nav screen (if any).
                        navController.navigate(screen.lastVisitedRoute) {
                            // popUpTo(navController.graph.findStartDestination().id)
                            popUpTo(screen.lastVisitedRoute)
                            launchSingleTop = true
                            restoreState = true
                        }
                        currentMainBottomNavRoute.value = screen.route
                    }
                },
                label = {
                    Text(
                        text = stringResource(id = screen.title),
                        style = MaterialTheme.typography.button
                    )
                },
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = stringResource(
                            id = R.string.back
                        )
                    )
                }
            )
        }
    }
}
