package com.rrg.dinnerrecommendation.models.keys

import com.rrg.dinnerrecommendation.utils.Constants

enum class RecommendationScreens(val route: String) {
    Dinner(Constants.ScreenRoute.DINNER),
    MealCategories(Constants.ScreenRoute.MEAL_CATEGORIES),
    DrinkCategories(Constants.ScreenRoute.DRINK_CATEGORIES),
    FinalRecommendation(Constants.ScreenRoute.FINAL_RECOMMENDATION),
    RecipeDetailsFromRecommendation(Constants.ScreenRoute.RECIPE_DETAILS_FROM_RECOMMENDED + "/{${Constants.NavigationArguments.ID}}/{${Constants.NavigationArguments.TYPE}}")
}
