package com.rrg.dinnerrecommendation.ui.recommendation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar
import com.rrg.dinnerrecommendation.ui.components.CocktailCategoryList
import com.rrg.dinnerrecommendation.ui.components.GenericError
import com.rrg.dinnerrecommendation.ui.components.NextButton
import com.rrg.dinnerrecommendation.utils.safeNavigateTo

@ExperimentalFoundationApi
@Composable
fun DrinkCategorySelection(
    navController: NavHostController,
    viewModel: RecommendationViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(RecommendationViewModel.RecommendationEvents.SearchCocktailCategories)
    }

    when (val state = viewModel.stateDrinks.value) {
        is State.Loading -> {
            CircularIndeterminateProgressBar()
        }
        is State.LoadingFailed -> {
            GenericError {
                viewModel.onEvent(RecommendationViewModel.RecommendationEvents.SearchCocktailCategories)
            }
        }
        is State.RetryLoading -> {
            GenericError(isLoading = true) {
            }
        }
        is State.Loaded -> {
            CocktailCategoryList(
                data = state.data,
                viewModel = viewModel
            )
            NextButton(
                onNextClick = {
                    viewModel.resetRecommendation()
                    navController.safeNavigateTo(RecommendationScreens.FinalRecommendation.route)
                },
                currentRecommendationScreen = RecommendationScreens.DrinkCategories,
                viewModel = viewModel
            )
        }
    }
}
