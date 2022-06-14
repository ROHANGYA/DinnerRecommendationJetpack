package com.rrg.dinnerrecommendation.ui.food_category_selection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar

@Composable
fun FoodCategorySelection() {
    val viewModelFood: FoodCategoryViewModel = hiltViewModel()
    // val context = LocalContext.current

    viewModelFood.getFoodCategories()
    val data: MutableState<List<MealCategory>> = remember {
        mutableStateOf(listOf())
    }

    val loading: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = Unit) {
        viewModelFood.categoriesPageEvents.collect {
            when (it) {
                is State.Loading -> {
                    loading.value = true
                }
                is State.LoadingFailed -> {
                    loading.value = false
                }
                is State.Loaded -> {
                    loading.value = false
                    data.value = it.data
                }
            }
        }
    }

    if (loading.value) {
        CircularIndeterminateProgressBar()
    } else {
        MealCategoriesList(data = data.value, onNextClick = {}, onSelected = { })
    }
}
