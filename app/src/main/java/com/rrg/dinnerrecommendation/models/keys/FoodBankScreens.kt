package com.rrg.dinnerrecommendation.models.keys

import com.rrg.dinnerrecommendation.utils.Constants

enum class FoodBankScreens(val route: String) {
    FoodBankList(Constants.ScreenRoute.FOOD_BANK),
    RecipeDetailsFromFoodBank(Constants.ScreenRoute.RECIPE_DETAILS_FROM_FOOD_BANK + "/{${Constants.NavigationArguments.ID}}/{${Constants.NavigationArguments.TYPE}}")
}
