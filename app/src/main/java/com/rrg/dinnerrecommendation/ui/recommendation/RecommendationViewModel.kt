package com.rrg.dinnerrecommendation.ui.recommendation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.Drink
import com.rrg.dinnerrecommendation.models.primary.DrinkCategory
import com.rrg.dinnerrecommendation.models.primary.Meal
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.service.primary.DrinkService
import com.rrg.dinnerrecommendation.service.primary.MealService
import com.rrg.dinnerrecommendation.utils.triggerActionIfNotLoaded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    private val mealService: MealService,
    private val drinkService: DrinkService
) : ViewModel() {

    val stateMeals = mutableStateOf<State<List<MealCategory>>>(State.Loading)
    val stateDrinks = mutableStateOf<State<List<DrinkCategory>>>(State.Loading)

    var selectedMealCategory = mutableStateOf<MealCategory?>(null)
    var selectedDrinkCategory = mutableStateOf<DrinkCategory?>(null)

    var currentlyViewingMealDetails = mutableStateOf<MealCategory?>(null)

    lateinit var selectedMealCategoryList: List<Meal>
    lateinit var selectedDrinkCategoryList: List<Drink>

    val stateRecommendedMeal = mutableStateOf<State<Meal>>(State.Loading)
    val stateRecommendedDrink = mutableStateOf<State<Drink>>(State.Loading)

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

    private fun getDrinkCategories() = viewModelScope.launch {
        when (val result = drinkService.getDrinkCategories()) {
            is Result.Success -> {
                stateDrinks.value = State.Loaded(result.value.drinks)
            }
            is Result.Failure -> {
                stateDrinks.value = State.LoadingFailed(result.error)
            }
        }
    }

    private fun getMealListByCategory() = viewModelScope.launch {
        selectedMealCategory.value?.strCategory?.let {
            when (val result = mealService.getMealListByCategory(it)) {
                is Result.Success -> {
                    selectedMealCategoryList = result.value.meals
                    stateRecommendedMeal.value = State.Loaded(result.value.meals.random())
                }
                is Result.Failure -> {
                    stateRecommendedMeal.value = State.LoadingFailed(result.error)
                }
            }
        }
    }

    private fun getDrinkListByCategory() = viewModelScope.launch {
        selectedDrinkCategory.value?.strCategory?.let {
            when (val result = drinkService.getDrinkListByCategory(it)) {
                is Result.Success -> {
                    selectedDrinkCategoryList = result.value.drinks
                    stateRecommendedDrink.value = State.Loaded(result.value.drinks.random())
                }
                is Result.Failure -> {
                    stateRecommendedDrink.value = State.LoadingFailed(result.error)
                }
            }
        }
    }

    fun onEvent(event: RecommendationEvents) = viewModelScope.launch {
        when (event) {
            RecommendationEvents.SearchMealCategories -> {
                stateMeals.value.triggerActionIfNotLoaded { getFoodCategories() }
            }
            RecommendationEvents.SearchCocktailCategories -> {
                stateDrinks.value.triggerActionIfNotLoaded { getDrinkCategories() }
            }
            RecommendationEvents.GetDinnerRecommendation -> {
                stateRecommendedMeal.value.triggerActionIfNotLoaded { getMealListByCategory() }
                stateRecommendedDrink.value.triggerActionIfNotLoaded { getDrinkListByCategory() }
            }
            RecommendationEvents.SuggestAnotherDinner -> {
                stateRecommendedMeal.value = State.Loaded(selectedMealCategoryList.random())
                stateRecommendedDrink.value = State.Loaded(selectedDrinkCategoryList.random())
            }
        }
    }

    sealed class RecommendationEvents {
        object SearchMealCategories : RecommendationEvents()
        object SearchCocktailCategories : RecommendationEvents()
        object GetDinnerRecommendation : RecommendationEvents()
        object SuggestAnotherDinner : RecommendationEvents()
    }
}
