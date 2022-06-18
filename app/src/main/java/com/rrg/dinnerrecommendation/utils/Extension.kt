package com.rrg.dinnerrecommendation.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.rrg.dinnerrecommendation.core.State

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

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

fun <T> State<T>.triggerActionIfNotLoaded(action: () -> Unit) {
    if (this !is State.Loaded) {
        action.invoke()
    }
}

fun Context.showToast(@StringRes text: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

fun String.addPathCurlyBrackets(): String {
    return "{$this}"
}
