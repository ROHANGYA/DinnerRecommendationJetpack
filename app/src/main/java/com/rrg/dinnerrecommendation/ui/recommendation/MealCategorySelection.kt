package com.rrg.dinnerrecommendation.ui.recommendation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar
import com.rrg.dinnerrecommendation.ui.components.MealCategoriesList
import com.rrg.dinnerrecommendation.ui.components.MealCategoryInfoDialog
import com.rrg.dinnerrecommendation.ui.components.NextButton
import com.rrg.dinnerrecommendation.utils.safeNavigateTo

@Composable
fun MealCategorySelection(
    navController: NavHostController,
    viewModel: RecommendationViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(RecommendationViewModel.RecommendationEvents.SearchMealCategories)
    }

    when (val state = viewModel.stateMeals.value) {
        is State.Loading -> {
            CircularIndeterminateProgressBar()
        }
        is State.LoadingFailed -> {
            // TODO -- add error compose
        }
        is State.Loaded -> {
            MealCategoriesList(
                data = state.data,
                viewModel = viewModel
            )
            NextButton(
                onNextClick = {
                    navController.safeNavigateTo(RecommendationScreens.DrinkCategories.route)
                },
                currentRecommendationScreen = RecommendationScreens.MealCategories,
                viewModel = viewModel
            )
            val currentlyViewingMealDetails = viewModel.currentlyViewingMealDetails.value
            if (currentlyViewingMealDetails != null) {
                MealCategoryInfoDialog(
                    title = currentlyViewingMealDetails.strCategory,
                    text = currentlyViewingMealDetails.getCleanCategoryDescription()
                ) {
                    viewModel.currentlyViewingMealDetails.value = null
                }
            }
        }
    }
}
