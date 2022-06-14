package com.rrg.dinnerrecommendation.ui.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar

@Composable
fun FoodCategorySelection() {
    val viewModel: CategoriesViewModel = hiltViewModel()
    val context = LocalContext.current

    viewModel.getFoodCategories()
    val data: MutableState<List<MealCategory>> = remember {
        mutableStateOf(listOf())
    }

    val loading: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = Unit){
        viewModel.categoriesPageEvents.collect {
            when(it){
                is CategoriesViewModel.CategoriesPageEvents.OnFailure -> {
                    loading.value = false
                }
                is CategoriesViewModel.CategoriesPageEvents.OnSuccess -> {
                    loading.value = false
                    data.value = it.data
                }
            }
        }
    }

    if(loading.value){
        CircularIndeterminateProgressBar()
    }else{
        MealCategoriesList(data = data.value, onNextClick = {}, onSelected = { } )
    }
}