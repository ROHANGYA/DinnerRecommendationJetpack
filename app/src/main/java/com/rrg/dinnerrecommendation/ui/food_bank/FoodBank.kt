package com.rrg.dinnerrecommendation.ui.food_bank

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.primary.FoodBankItem
import com.rrg.dinnerrecommendation.ui.components.FoodBankList
import com.rrg.dinnerrecommendation.ui.components.FoodBankTab
import com.rrg.dinnerrecommendation.ui.components.GenericError
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@ExperimentalFoundationApi
@Composable
fun FoodBank(navController: NavController, viewModel: FoodBankViewModel) {
    val isLoading: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }
    val data: MutableState<List<FoodBankItem>?> = remember {
        mutableStateOf(listOf())
    }
    val isError: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    when (val state = viewModel.searchState.value) {
        State.Loading -> {
            isError.value = false
            isLoading.value = true
        }
        is State.Loaded -> {
            isError.value = false
            isLoading.value = false
            data.value = state.data
        }
        is State.LoadingFailed -> {
            isLoading.value = false
            isError.value = true
        }
    }
    if(isError.value){
        GenericError {
            viewModel.updateSearchQuery()
        }
    }else{
        Column {
            FoodBankTab(
                viewModel.currentRecipeType,
                viewModel::updateTab
            )
            FoodBankList(
                data.value,
                navController,
                viewModel.searchQuery,
                viewModel::updateSearchQuery,
                isLoading.value
            )
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun PreviewFoodBankList() {
    DinnerRecommendationJetpackTheme {
        FoodBank(rememberNavController(), viewModel())
    }
}
