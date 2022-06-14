package com.rrg.dinnerrecommendation.utils

import androidx.navigation.NavController

fun NavController.safeNavigateTo(route: String) {
    this.navigate(route) {
        popUpTo(route) {
            inclusive = true
        }
    }
}

fun String.addEmptyLines(lines: Int) = this + "\n".repeat(lines)
