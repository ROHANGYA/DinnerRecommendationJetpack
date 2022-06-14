package com.rrg.dinnerrecommendation.ui.cocktail_category_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrg.dinnerrecommendation.core.Result
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.CocktailCategory
import com.rrg.dinnerrecommendation.service.primary.CocktailService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailCategoryViewModel @Inject constructor(
    private val cocktailService: CocktailService
) : ViewModel() {

    private val cocktailCategoryChannel = Channel<State<List<CocktailCategory>>>()
    val cocktailCategoryEvents = cocktailCategoryChannel.receiveAsFlow()

    init {
        getCocktailCategories()
    }

    private fun getCocktailCategories() = viewModelScope.launch {
        cocktailCategoryChannel.send(State.Loading)
        when (val result = cocktailService.getCocktailCategories()) {
            is Result.Failure -> {
                cocktailCategoryChannel.send(State.LoadingFailed(result.error))
            }
            is Result.Success -> {
                cocktailCategoryChannel.send(State.Loaded(result.value.drinks))
            }
        }
    }
}
