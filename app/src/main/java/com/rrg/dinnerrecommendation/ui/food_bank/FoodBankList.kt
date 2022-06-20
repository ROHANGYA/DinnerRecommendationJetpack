package com.rrg.dinnerrecommendation.ui.food_bank

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.rrg.dinnerrecommendation.models.keys.FoodBankScreens
import com.rrg.dinnerrecommendation.models.keys.RecipeCategories
import com.rrg.dinnerrecommendation.utils.Constants
import com.rrg.dinnerrecommendation.utils.addPathCurlyBrackets
import com.rrg.dinnerrecommendation.utils.safeNavigateTo

@Composable
fun FoodBankList(navController: NavController) {
    Text(
        text = "Food Bank",
        modifier = Modifier.fillMaxSize().clickable {
            navController.safeNavigateTo(
                FoodBankScreens.RecipeDetailsFromFoodBank.route
                    .replace(Constants.NavigationArguments.ID.addPathCurlyBrackets(), "11007")
                    .replace(Constants.NavigationArguments.TYPE.addPathCurlyBrackets(), RecipeCategories.Drink.name)
            )
        },
        textAlign = TextAlign.Center
    )
}
