package com.rrg.dinnerrecommendation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController

fun NavController.safeNavigateTo(route: String) {
    this.navigate(route) {
        popUpTo(route) {
            inclusive = true
        }
    }
}

fun String.addEmptyLines(lines: Int) = this + "\n".repeat(lines)

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.createViewModelScopedByRoute(
    navController: NavHostController,
    route: String
): T {
    val routeEntry = remember(this) {
        navController.getBackStackEntry(route)
    }
    return hiltViewModel(routeEntry)
}
