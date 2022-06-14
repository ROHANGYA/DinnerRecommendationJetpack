package com.rrg.dinnerrecommendation.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rrg.dinnerrecommendation.models.keys.BottomBarScreens
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.ui.FoodBankList
import com.rrg.dinnerrecommendation.ui.LandingPage
import com.rrg.dinnerrecommendation.ui.SettingsPage
import com.rrg.dinnerrecommendation.ui.drink_category_selection.CocktailCategorySelection
import com.rrg.dinnerrecommendation.ui.food_category_selection.MealCategorySelection

@ExperimentalFoundationApi
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Recommendation.route
    ) {
        composable(route = BottomBarScreens.Recommendation.route) {
            LandingPage(navController = navController)
        }
        composable(route = BottomBarScreens.FoodBank.route) {
            FoodBankList()
        }
        composable(route = BottomBarScreens.Settings.route) {
            SettingsPage()
        }
        composable(route = RecommendationScreens.MealCategories.route) {
            MealCategorySelection(navController)
        }
        composable(route = RecommendationScreens.CocktailCategories.route) {
            CocktailCategorySelection(navController)
        }
        composable(route = RecommendationScreens.FinalRecommendation.route) {
            // TODO fill up navigation when compose screens are done
        }
        composable(route = RecommendationScreens.Recipe.route) {
            // TODO fill up navigation when compose screens are done
        }
    }
}
