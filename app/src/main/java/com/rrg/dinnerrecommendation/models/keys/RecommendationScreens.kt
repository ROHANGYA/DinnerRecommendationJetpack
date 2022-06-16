package com.rrg.dinnerrecommendation.models.keys

enum class RecommendationScreens(val route: String) {
    MealCategories("meal_categories"),
    DrinkCategories("drink_categories"),
    FinalRecommendation("recommendation_page"),
    Recipe("Recipe")
}
