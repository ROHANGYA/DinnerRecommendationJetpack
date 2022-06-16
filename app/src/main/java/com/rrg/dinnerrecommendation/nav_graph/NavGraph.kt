package com.rrg.dinnerrecommendation.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.models.keys.BottomBarScreens
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.ui.FoodBankList
import com.rrg.dinnerrecommendation.ui.LandingPage
import com.rrg.dinnerrecommendation.ui.MainViewModel
import com.rrg.dinnerrecommendation.ui.SettingsPage
import com.rrg.dinnerrecommendation.ui.recommendation.CocktailCategorySelection
import com.rrg.dinnerrecommendation.ui.recommendation.MealCategorySelection

@ExperimentalFoundationApi
@Composable
fun NavGraph(navController: NavHostController, mainViewModel: MainViewModel) {
/*
    // ViewModelSharedAcrossAllApp
    val mainViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }
    val mainViewModel: MainViewModel = hiltViewModel(mainViewModelStoreOwner)
*/
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Recommendation.route
    ) {
        composable(route = BottomBarScreens.Recommendation.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.dinner_recommendation), false)
            LandingPage(navController = navController)
        }
        composable(route = BottomBarScreens.FoodBank.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.food_bank))
            FoodBankList()
        }
        composable(route = BottomBarScreens.Settings.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.settings))
            SettingsPage()
        }
        composable(route = RecommendationScreens.MealCategories.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.meal_categories))
            MealCategorySelection(navController)
        }
        composable(route = RecommendationScreens.CocktailCategories.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.drink_categories))
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
