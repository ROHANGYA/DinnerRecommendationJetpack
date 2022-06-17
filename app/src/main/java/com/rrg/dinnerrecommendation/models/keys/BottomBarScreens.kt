package com.rrg.dinnerrecommendation.models.keys

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.utils.Constants

enum class BottomBarScreens(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val childrenRoutes: List<String>,
    var lastVisitedRoute: String
) {
    Recommendation(
        Constants.ScreenRoute.RECOMMENDATION,
        R.string.dinner,
        R.drawable.ic_round_dining,
        RecommendationScreens.values().map { it.route },
        Constants.ScreenRoute.RECOMMENDATION
    ),
    FoodBank(
        Constants.ScreenRoute.FOOD_BANK,
        R.string.food_bank,
        R.drawable.ic_round_food_bank,
        listOf(),
        Constants.ScreenRoute.FOOD_BANK
    ),
    Settings(
        Constants.ScreenRoute.SETTINGS,
        R.string.settings,
        R.drawable.ic_round_settings,
        listOf(),
        Constants.ScreenRoute.SETTINGS
    )
}
