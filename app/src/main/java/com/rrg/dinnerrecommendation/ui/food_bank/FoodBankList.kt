package com.rrg.dinnerrecommendation.ui.food_bank

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.Meal
import com.rrg.dinnerrecommendation.ui.components.RecipeList
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@ExperimentalFoundationApi
@Composable
fun FoodBankList(navController: NavController, viewModel: FoodBankViewModel) {
    val isLoading: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }
    val data: MutableState<List<Meal>> = remember {
        mutableStateOf(listOf())
    }

    when (val state = viewModel.searchState.value) {
        State.Loading -> {
            isLoading.value = true
        }
        is State.Loaded -> {
            isLoading.value = false
            data.value = state.data
        }
        is State.LoadingFailed -> {
            isLoading.value = false
            // TODO
        }
    }
    RecipeList(
        data.value,
        navController,
        viewModel.searchQuery,
        viewModel::updateSearchQuery,
        isLoading.value
    )
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun PreviewFoodBankList() {
    DinnerRecommendationJetpackTheme {
        FoodBankList(rememberNavController(), viewModel())
    }
}
