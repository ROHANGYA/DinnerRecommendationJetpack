package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.models.keys.FoodBankScreens
import com.rrg.dinnerrecommendation.models.keys.RecipeCategories
import com.rrg.dinnerrecommendation.models.primary.FoodBankItem
import com.rrg.dinnerrecommendation.models.primary.Meal
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.utils.Constants
import com.rrg.dinnerrecommendation.utils.addPathCurlyBrackets
import com.rrg.dinnerrecommendation.utils.safeNavigateTo

@ExperimentalFoundationApi
@Composable
fun FoodBankList(
    data: List<FoodBankItem>?,
    navController: NavController,
    searchQuery: MutableState<String>,
    searchUpdate: (String) -> Unit,
    isLoading: Boolean
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        stickyHeader {
            FoodBankSearchTextField(searchQuery = searchQuery, searchUpdate)
        }
        if (isLoading) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
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
                        item.name,
                        item.thumb,
                        listOf(item.area, item.category, item.strAlcoholic)
                    ) {
                        navController.safeNavigateTo(
                            FoodBankScreens.RecipeDetailsFromFoodBank.route
                                .replace(
                                    Constants.NavigationArguments.ID.addPathCurlyBrackets(),
                                    item.id
                                )
                                .replace(
                                    Constants.NavigationArguments.TYPE.addPathCurlyBrackets(),
                                    item.type.name
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
                FoodBankItem("test", "test", "test", type = RecipeCategories.Meal),
                FoodBankItem("testing", "test", "test", type = RecipeCategories.Meal),
                FoodBankItem("haha", "test", "test", type = RecipeCategories.Meal),
                FoodBankItem("super ling meal name to test", "test", "test", type = RecipeCategories.Meal),
                FoodBankItem("hehehehe", "test", "test", type = RecipeCategories.Meal),
                FoodBankItem("hihihihihii", "test", "test", type = RecipeCategories.Meal),
            ),
            rememberNavController(),
            remember { mutableStateOf("test search") },
            { },
            false
        )
    }
}
