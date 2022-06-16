package com.rrg.dinnerrecommendation.ui.recommendation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.CocktailCategory
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.service.primary.CocktailService
import com.rrg.dinnerrecommendation.service.primary.MealService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    private val mealService: MealService,
    private val cocktailService: CocktailService
) : ViewModel() {

    private val mealCategoryChannel = Channel<State<List<MealCategory>>>()
    val mealCategoryEvents = mealCategoryChannel.receiveAsFlow()

    private val _stateMeals = MutableStateFlow<State<List<MealCategory>>>(State.Loading)
    val stateMeals = _stateMeals.asStateFlow()

    private val _stateCocktail = MutableStateFlow<State<List<CocktailCategory>>>(State.Loading)
    val stateCocktail = _stateCocktail.asStateFlow()

    init {
        getFoodCategories()
    }

    fun getFoodCategories() = viewModelScope.launch {
       //mealCategoryChannel.send(State.Loading)
        when (val result = mealService.getMealCategories()) {
            is Result.Success -> {
               // mealCategoryChannel.send(State.Loaded(result.value.categories))
                _stateMeals.value = State.Loaded(result.value.categories)
            }
            is Result.Failure -> {
                //mealCategoryChannel.send(State.LoadingFailed(result.error))
                _stateMeals.value = State.LoadingFailed(result.error)
            }
        }
    }

    fun getCocktailCategories() = viewModelScope.launch {
        when (val result = cocktailService.getCocktailCategories()) {
            is Result.Success -> {
                _stateCocktail.value = State.Loaded(result.value.drinks)
            }
            is Result.Failure -> {
                _stateCocktail.value = State.LoadingFailed(result.error)
            }
        }
    }
}
