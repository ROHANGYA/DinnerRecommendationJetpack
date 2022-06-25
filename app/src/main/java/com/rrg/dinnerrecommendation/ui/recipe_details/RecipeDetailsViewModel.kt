package com.rrg.dinnerrecommendation.ui.recipe_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.Drink
import com.rrg.dinnerrecommendation.models.primary.Meal
import com.rrg.dinnerrecommendation.service.primary.DrinkService
import com.rrg.dinnerrecommendation.service.primary.MealService
import com.rrg.dinnerrecommendation.utils.triggerActionIfNotLoaded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val mealService: MealService,
    private val drinkService: DrinkService
) : ViewModel() {

    val mealDetails = mutableStateOf<State<Meal?>>(State.Loading)
    val drinkDetails = mutableStateOf<State<Drink?>>(State.Loading)

    private fun getMealDetails(mealId: String) = viewModelScope.launch {
        when (val result = mealService.getMealById(mealId)) {
            is Result.Failure -> {
                mealDetails.value = State.LoadingFailed(result.error)
            }
            is Result.Success -> {
                mealDetails.value = State.Loaded(result.value.meals?.first())
            }
        }
    }

    private fun getDrinkDetails(drinkId: String) = viewModelScope.launch {
        when (val result = drinkService.getDrinkById(drinkId)) {
            is Result.Failure -> {
                drinkDetails.value = State.LoadingFailed(result.error)
            }
            is Result.Success -> {
                drinkDetails.value = State.Loaded(result.value.drinks?.first())
            }
        }
    }

    fun onEvent(event: RecipeDetailsEvents) {
        when (event) {
            is RecipeDetailsEvents.FetchDrinkDetails -> {
                drinkDetails.triggerActionIfNotLoaded { getDrinkDetails(event.drinkId) }
            }
            is RecipeDetailsEvents.FetchMealDetails -> {
                mealDetails.triggerActionIfNotLoaded { getMealDetails(event.mealId) }
            }
        }
    }

    sealed class RecipeDetailsEvents {
        data class FetchMealDetails(val mealId: String) : RecipeDetailsEvents()
        data class FetchDrinkDetails(val drinkId: String) : RecipeDetailsEvents()
    }
}
