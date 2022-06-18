package com.rrg.dinnerrecommendation.models.keys

import com.rrg.dinnerrecommendation.utils.Constants

enum class SharedScreens(val route: String) {
    RecipeDetails(Constants.ScreenRoute.RECIPE_DETAILS + "/{${Constants.NavigationArguments.ID}}/{${Constants.NavigationArguments.TYPE}}")
}
