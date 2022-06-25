package com.rrg.dinnerrecommendation.ui.food_bank

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.keys.RecipeCategories
import com.rrg.dinnerrecommendation.models.primary.FoodBankItem
import com.rrg.dinnerrecommendation.models.primary.Meal
import com.rrg.dinnerrecommendation.service.primary.DrinkService
import com.rrg.dinnerrecommendation.service.primary.MealService
import com.rrg.dinnerrecommendation.utils.applyRetryLoadingIfFailed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodBankViewModel @Inject constructor(
    private val mealService: MealService,
    private val drinkService: DrinkService
) : ViewModel() {
    var searchQuery: MutableState<String> = mutableStateOf("")
    val currentRecipeType: MutableState<RecipeCategories> = mutableStateOf(RecipeCategories.Meal)
    val searchState: MutableState<State<List<FoodBankItem>>> = mutableStateOf(State.Loading)

    init {
        loadData()
    }

    private fun searchMeals() = viewModelScope.launch {
        searchState.applyRetryLoadingIfFailed()
        when (val result = mealService.searchMeals(searchQuery.value)) {
            is Result.Failure -> {
                searchState.value = State.LoadingFailed(result.error)
            }
            is Result.Success -> {
                val processedList = ArrayList<FoodBankItem>()
                result.value.meals?.forEach {
                    processedList.add(
                        FoodBankItem(
                            name = it.strMeal,
                            thumb = it.strMealThumb,
                            id = it.idMeal,
                            category = it.strCategory,
                            area = it.strArea,
                            type = RecipeCategories.Meal
                        )
                    )
                }
                searchState.value = State.Loaded(processedList)
            }
        }
    }

    private fun searchDrinks() = viewModelScope.launch {
        searchState.applyRetryLoadingIfFailed()
        when (val result = drinkService.searchDrinks(searchQuery.value)) {
            is Result.Failure -> {
                searchState.value = State.LoadingFailed(result.error)
            }
            is Result.Success -> {
                val processedList = ArrayList<FoodBankItem>()
                result.value.drinks?.forEach {
                    processedList.add(
                        FoodBankItem(
                            name = it.strDrink,
                            thumb = it.strDrinkThumb,
                            id = it.idDrink,
                            category = it.strCategory,
                            strAlcoholic = it.strAlcoholic,
                            type = RecipeCategories.Drink
                        )
                    )
                }
                searchState.value = State.Loaded(processedList)
            }
        }
    }

    private fun loadData() {
        when (currentRecipeType.value) {
            RecipeCategories.Meal -> {
                searchMeals()
            }
            RecipeCategories.Drink -> {
                searchDrinks()
            }
        }
    }

    fun updateTab(tabIndex: Int) {
        currentRecipeType.value = RecipeCategories.values()[tabIndex]
        loadData()
    }

    fun updateSearchQuery(search: String? = null) {
        search?.let {
            searchQuery.value = it
        }
        loadData()
    }
}
