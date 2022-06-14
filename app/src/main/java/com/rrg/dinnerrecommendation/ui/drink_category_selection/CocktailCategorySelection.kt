package com.rrg.dinnerrecommendation.ui.drink_category_selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.CocktailCategory
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar
import com.rrg.dinnerrecommendation.ui.components.CocktailCategoryList

@ExperimentalFoundationApi
@Composable
fun CocktailCategorySelection(navController: NavHostController) {
    val viewModel: CocktailCategoryViewModel = hiltViewModel()
    // viewModel.getCocktailCategories()

    val data: MutableState<List<CocktailCategory>> = remember {
        mutableStateOf(listOf())
    }

    val isLoading: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.cocktailCategoryEvents.collect {
            when (it) {
                is State.Loaded -> {
                    isLoading.value = false
                    data.value = it.data
                }
                is State.Loading -> {
                    isLoading.value = true
                }
                is State.LoadingFailed -> {
                    isLoading.value = false
                    // TODO -- add error compose
                }
            }
        }
    }

    if (isLoading.value) {
        CircularIndeterminateProgressBar()
    } else {
        CocktailCategoryList(
            data = data.value,
            onSelected = { },
            onNextClick = { }
        )
    }
}
