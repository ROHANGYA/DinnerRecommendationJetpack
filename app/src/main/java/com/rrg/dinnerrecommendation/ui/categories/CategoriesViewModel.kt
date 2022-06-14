package com.rrg.dinnerrecommendation.ui.categories

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.service.primary.MealService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val mealService: MealService
) : ViewModel() {

    private val categoriesPageChannel = Channel<CategoriesPageEvents>()
    val categoriesPageEvents = categoriesPageChannel.receiveAsFlow()

    // val test: MutableState<List<MealCategory>> = mutableStateOf(ArrayList()) //Alternative

    fun onEvent(event: CategoriesPageEvents) {
    }

    fun getFoodCategories() = viewModelScope.launch {
        when (val result = mealService.getMealCategories()) {
            is Result.Success -> {
                categoriesPageChannel.send(CategoriesPageEvents.OnSuccess(result.value.categories))
                //test.value = result.value.categories //Alternative
            }
            is Result.Failure -> {
                categoriesPageChannel.send(CategoriesPageEvents.OnFailure(result.error.serverErrorMessage))
            }
        }
    }

    sealed class CategoriesPageEvents {
        data class OnSuccess(val data: List<MealCategory>) : CategoriesPageEvents()
        data class OnFailure(val error: String?) : CategoriesPageEvents()
    }
}
