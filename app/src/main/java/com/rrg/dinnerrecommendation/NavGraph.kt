package com.rrg.dinnerrecommendation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rrg.dinnerrecommendation.models.BottomBarScreens
import com.rrg.dinnerrecommendation.ui.FoodBankList
import com.rrg.dinnerrecommendation.ui.LandingPage
import com.rrg.dinnerrecommendation.ui.SettingsPage

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Recommendation.route
    ) {
        composable(route = BottomBarScreens.Recommendation.route) {
            LandingPage()
        }
        composable(route = BottomBarScreens.FoodBank.route) {
            FoodBankList()
        }
        composable(route = BottomBarScreens.Settings.route) {
            SettingsPage()
        }
    }

}