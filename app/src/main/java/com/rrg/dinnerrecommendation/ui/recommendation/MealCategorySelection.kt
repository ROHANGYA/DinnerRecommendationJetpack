package com.rrg.dinnerrecommendation.ui.recommendation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar
import com.rrg.dinnerrecommendation.ui.components.MealCategoriesList
import com.rrg.dinnerrecommendation.utils.safeNavigateTo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun MealCategorySelection(navController: NavHostController) {
    // val context = LocalContext.current
    val viewModelMeal: RecommendationViewModel = hiltViewModel()
    // viewModelMeal.getFoodCategories()
    val scope = rememberCoroutineScope()

    val data: MutableState<List<MealCategory>> = remember {
        mutableStateOf(listOf())
    }

    val loading: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = Unit) {
        viewModelMeal.stateMeals.collect {
            when (it) {
                is State.Loading -> {
                    loading.value = true
                }
                is State.LoadingFailed -> {
                    loading.value = false
                    // TODO -- add error compose
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
        MealCategoriesList(
            data = data.value,
            onNextClick = { navController.safeNavigateTo(RecommendationScreens.CocktailCategories.route) },
            onSelected = {}
        )
    }
}
