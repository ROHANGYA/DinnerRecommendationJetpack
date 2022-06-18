package com.rrg.dinnerrecommendation.models.keys

import com.rrg.dinnerrecommendation.utils.Constants

enum class RecommendationScreens(val route: String) {
    MealCategories(Constants.ScreenRoute.MEAL_CATEGORIES),
    DrinkCategories(Constants.ScreenRoute.DRINK_CATEGORIES),
    FinalRecommendation(Constants.ScreenRoute.FINAL_RECOMMENDATION)
}
