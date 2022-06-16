package com.rrg.dinnerrecommendation.ui.recommendation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar
import com.rrg.dinnerrecommendation.ui.components.MealCategoriesList
import com.rrg.dinnerrecommendation.utils.safeNavigateTo

@Composable
fun MealCategorySelection(navController: NavHostController) {
    val viewModel: RecommendationViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.onEvent(RecommendationViewModel.RecommendationEvents.SearchMealCategories)
    }

    val data: MutableState<List<MealCategory>> = remember {
        mutableStateOf(listOf())
    }

    val isLoading: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }

    when (val state = viewModel.stateMeals.value) {
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
        MealCategoriesList(
            data = data.value,
            onNextClick = { navController.safeNavigateTo(RecommendationScreens.CocktailCategories.route) },
            viewModel = viewModel
        )
    }
}