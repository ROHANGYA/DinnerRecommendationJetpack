package com.rrg.dinnerrecommendation.models.keys

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rrg.dinnerrecommendation.R

enum class BottomBarScreens(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val childrenRoutes: List<String>
) {
    Recommendation(
        RecommendationScreens.Dinner.route,
        R.string.dinner,
        R.drawable.ic_round_dining,
        RecommendationScreens.values().map { it.route }
    ),
    FoodBank(
        FoodBankScreens.FoodBankList.route,
        R.string.food_bank,
        R.drawable.ic_round_food_bank,
        FoodBankScreens.values().map { it.route }
    ),
    Settings(
        SettingsScreens.Settings.route,
        R.string.settings,
        R.drawable.ic_round_settings,
        SettingsScreens.values().map { it.route }
    )
}
