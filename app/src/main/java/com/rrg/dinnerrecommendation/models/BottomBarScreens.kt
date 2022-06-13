package com.rrg.dinnerrecommendation.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rrg.dinnerrecommendation.R

enum class BottomBarScreens(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    Recommendation("recommendation", R.string.recommendation, R.drawable.ic_round_dining),
    FoodBank("food_bank", R.string.food_bank, R.drawable.ic_round_food_bank),
    Settings("settings", R.string.settings, R.drawable.ic_round_settings)
}
