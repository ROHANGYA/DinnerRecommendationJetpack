package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.models.keys.FoodBankScreens
import com.rrg.dinnerrecommendation.models.keys.RecipeCategories
import com.rrg.dinnerrecommendation.models.primary.Meal
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.utils.Constants
import com.rrg.dinnerrecommendation.utils.addPathCurlyBrackets
import com.rrg.dinnerrecommendation.utils.safeNavigateTo

@ExperimentalFoundationApi
@Composable
fun FoodBankList(
    data: List<Meal>?,
    navController: NavController,
    searchQuery: MutableState<String>,
    searchUpdate: (String) -> Unit,
    isLoading: Boolean
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        stickyHeader {
            RecipeSearchTextField(searchQuery = searchQuery, searchUpdate)
        }
        if (isLoading) {
            item {
                CircularIndeterminateProgressBar()
            }
        } else {
            if (data.isNullOrEmpty()) {
                item {
                    EmptyPlaceholder()
                }
            } else {
                items(data) { item ->
                    FoodBankItem(
                        item.strMeal,
                        item.strMealThumb,
                        listOf(item.strArea, item.strCategory)
                    ) {
                        navController.safeNavigateTo(
                            FoodBankScreens.RecipeDetailsFromFoodBank.route
                                .replace(
                                    Constants.NavigationArguments.ID.addPathCurlyBrackets(),
                                    item.idMeal
                                )
                                .replace(
                                    Constants.NavigationArguments.TYPE.addPathCurlyBrackets(),
                                    RecipeCategories.Meal.name
                                )
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewFoodBankList() {
    DinnerRecommendationJetpackTheme {
        FoodBankList(
            data = listOf(
                Meal("test", "test", "test"),
                Meal("testing", "test", "test"),
                Meal("haha", "test", "test"),
                Meal("super ling meal name to test", "test", "test"),
                Meal("hehehehe", "test", "test"),
                Meal("hihihihihii", "test", "test"),
            ),
            rememberNavController(),
            remember { mutableStateOf("test search") },
            { },
            false
        )
    }
}
