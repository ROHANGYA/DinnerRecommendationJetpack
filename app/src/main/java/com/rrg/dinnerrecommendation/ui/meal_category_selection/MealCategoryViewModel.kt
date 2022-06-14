package com.rrg.dinnerrecommendation.ui.meal_category_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.service.primary.MealService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealCategoryViewModel @Inject constructor(
    private val mealService: MealService
) : ViewModel() {

    private val mealCategoryChannel = Channel<State<List<MealCategory>>>()
    val mealCategoryEvents = mealCategoryChannel.receiveAsFlow()

    // val test: MutableState<List<MealCategory>> = mutableStateOf(ArrayList()) //Alternative

    init {
        getFoodCategories()
    }

    private fun getFoodCategories() = viewModelScope.launch {
        mealCategoryChannel.send(State.Loading)
        when (val result = mealService.getMealCategories()) {
            is Result.Success -> {
                mealCategoryChannel.send(State.Loaded(result.value.categories))
                // test.value = result.value.categories //Alternative
            }
            is Result.Failure -> {
                mealCategoryChannel.send(State.LoadingFailed(result.error))
            }
        }
    }
}
