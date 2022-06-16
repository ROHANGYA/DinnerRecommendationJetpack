package com.rrg.dinnerrecommendation.ui.recommendation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.CocktailCategory
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.service.primary.CocktailService
import com.rrg.dinnerrecommendation.service.primary.MealService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    private val mealService: MealService,
    private val cocktailService: CocktailService
) : ViewModel() {

    val stateMeals = mutableStateOf<State<List<MealCategory>>>(State.Loading)
    val stateCocktail = mutableStateOf<State<List<CocktailCategory>>>(State.Loading)

    var selectedMealCategory = mutableStateOf<MealCategory?>(null)
    var selectedDrinkCategory = mutableStateOf<CocktailCategory?>(null)

    var currentlyViewingMealDetails = mutableStateOf<MealCategory?>(null)

    private fun getFoodCategories() = viewModelScope.launch {

        when (val result = mealService.getMealCategories()) {
            is Result.Success -> {
                stateMeals.value = State.Loaded(result.value.categories)
            }
            is Result.Failure -> {
                stateMeals.value = State.LoadingFailed(result.error)
            }
        }
    }

    private fun getCocktailCategories() = viewModelScope.launch {
        when (val result = cocktailService.getCocktailCategories()) {
            is Result.Success -> {
                stateCocktail.value = State.Loaded(result.value.drinks)
            }
            is Result.Failure -> {
                stateCocktail.value = State.LoadingFailed(result.error)
            }
        }
    }

    fun onEvent(event: RecommendationEvents) = viewModelScope.launch {
        when (event) {
            RecommendationEvents.SearchMealCategories -> {
                getFoodCategories()
            }
            RecommendationEvents.SearchCocktailCategories -> {
                getCocktailCategories()
            }
        }
    }

    sealed class RecommendationEvents {
        object SearchMealCategories : RecommendationEvents()
        object SearchCocktailCategories : RecommendationEvents()
    }
}
