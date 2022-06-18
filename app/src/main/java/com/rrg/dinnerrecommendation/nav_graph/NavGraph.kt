package com.rrg.dinnerrecommendation.nav_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.models.keys.BottomBarScreens
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.models.keys.SharedScreens
import com.rrg.dinnerrecommendation.ui.FoodBankList
import com.rrg.dinnerrecommendation.ui.LandingPage
import com.rrg.dinnerrecommendation.ui.MainViewModel
import com.rrg.dinnerrecommendation.ui.SettingsPage
import com.rrg.dinnerrecommendation.ui.recipe_details.RecipeDetails
import com.rrg.dinnerrecommendation.ui.recommendation.DinnerRecommendation
import com.rrg.dinnerrecommendation.ui.recommendation.DrinkCategorySelection
import com.rrg.dinnerrecommendation.ui.recommendation.MealCategorySelection
import com.rrg.dinnerrecommendation.ui.recommendation.RecommendationViewModel
import com.rrg.dinnerrecommendation.utils.Constants
import com.rrg.dinnerrecommendation.utils.createViewModelScopedByRoute

@ExperimentalFoundationApi
@Composable
fun NavGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Recommendation.route
    ) {
        composable(route = BottomBarScreens.Recommendation.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.dinner_recommendation), false)
            LandingPage(navController = navController)
        }
        composable(route = BottomBarScreens.FoodBank.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.food_bank), false)
            FoodBankList()
        }
        composable(route = BottomBarScreens.Settings.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.settings), false)
            SettingsPage()
        }
        composable(route = RecommendationScreens.MealCategories.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.meal_categories))
            MealCategorySelection(
                navController,
                createRecommendationSharedViewModel(it, navController)
            )
        }
        composable(route = RecommendationScreens.DrinkCategories.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.drink_categories))
            DrinkCategorySelection(
                navController,
                createRecommendationSharedViewModel(it, navController)
            )
        }
        composable(route = RecommendationScreens.FinalRecommendation.route) {
            mainViewModel.updateToolbar(stringResource(id = R.string.dinner_recommendation))
            DinnerRecommendation(
                navController,
                createRecommendationSharedViewModel(it, navController)
            )
        }
        composable(
            route = SharedScreens.RecipeDetails.route,
            arguments = listOf(
                navArgument(Constants.NavigationArguments.ID) {
                    type = NavType.StringType
                },
                navArgument(Constants.NavigationArguments.TYPE) {
                    type = NavType.StringType
                }
            )
        ) {
            val idArgument = it.arguments?.getString(Constants.NavigationArguments.ID).toString()
            val category = it.arguments?.getString(Constants.NavigationArguments.TYPE).toString()
            RecipeDetails(navController, idArgument, category)
        }
    }
}

@Composable
private fun createRecommendationSharedViewModel(
    navBackStackEntry: NavBackStackEntry,
    navController: NavHostController
): RecommendationViewModel {
    return navBackStackEntry.createViewModelScopedByRoute(
        navController = navController,
        route = RecommendationScreens.MealCategories.route
    )
}
