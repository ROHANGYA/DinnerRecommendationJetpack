package com.rrg.dinnerrecommendation.ui.recommendation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.models.primary.DrinkCategory
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar
import com.rrg.dinnerrecommendation.ui.components.CocktailCategoryList
import com.rrg.dinnerrecommendation.ui.components.NextButton
import com.rrg.dinnerrecommendation.utils.safeNavigateTo

@ExperimentalFoundationApi
@Composable
fun DrinkCategorySelection(
    navController: NavHostController,
    viewModel: RecommendationViewModel
) {
    // val viewModel: RecommendationViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.onEvent(RecommendationViewModel.RecommendationEvents.SearchCocktailCategories)
    }

    val data: MutableState<List<DrinkCategory>> = remember {
        mutableStateOf(listOf())
    }

    val isLoading: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }

    when (val state = viewModel.stateDrinks.value) {
        is State.Loading -> {
            isLoading.value = true
        }
        is State.LoadingFailed -> {
            isLoading.value = false
            // TODO -- add error compose
        }
        is State.Loaded -> {
            isLoading.value = false
            data.value = state.data
        }
    }

    if (isLoading.value) {
        CircularIndeterminateProgressBar()
    } else {
        Text(text = viewModel.selectedMealCategory.value?.strCategory ?: "null")
        CocktailCategoryList(
            data = data.value,
            viewModel = viewModel
        )
        NextButton(
            onNextClick = { navController.safeNavigateTo(RecommendationScreens.FinalRecommendation.route) },
            currentRecommendationScreen = RecommendationScreens.DrinkCategories,
            viewModel = viewModel
        )
    }
}
