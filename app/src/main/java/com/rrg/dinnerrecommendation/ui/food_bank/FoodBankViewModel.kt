package com.rrg.dinnerrecommendation.ui.food_bank

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.Meal
import com.rrg.dinnerrecommendation.service.primary.DrinkService
import com.rrg.dinnerrecommendation.service.primary.MealService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodBankViewModel @Inject constructor(
    private val mealService: MealService,
    private val drinkService: DrinkService
) : ViewModel() {
    var searchQuery: MutableState<String> = mutableStateOf("")
    val searchState: MutableState<State<List<Meal>>> = mutableStateOf(State.Loading)

    init {
        searchMeals()
        // searchDrinks()
    }

    private fun searchMeals() = viewModelScope.launch {
        searchState.value = State.Loading
        when (val result = mealService.searchMeals(searchQuery.value)) {
            is Result.Failure -> {
                searchState.value = State.LoadingFailed(result.error)
            }
            is Result.Success -> {
                searchState.value = State.Loaded(result.value.meals)
            }
        }
    }

    private fun searchDrinks() = viewModelScope.launch {
        searchState.value = State.Loading
        when (val result = drinkService.searchDrinks(searchQuery.value)) {
            is Result.Failure -> {
                searchState.value = State.LoadingFailed(result.error)
            }
            is Result.Success -> {
                // searchState.value = State.Loaded(processedList)
            }
        }
    }

    fun updateSearchQuery(search: String) {
        searchQuery.value = search
        searchMeals()
        // searchDrinks()
    }
}
