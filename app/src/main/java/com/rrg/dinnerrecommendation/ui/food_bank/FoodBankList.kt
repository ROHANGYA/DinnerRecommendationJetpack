package com.rrg.dinnerrecommendation.ui.food_bank

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.ui.components.RecipeList
import com.rrg.dinnerrecommendation.ui.components.RecipeSearchTextField
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@Composable
fun FoodBankList(navController: NavController) {

    val searchQuery: MutableState<String> = remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        RecipeSearchTextField(searchQuery = searchQuery)
        Spacer(modifier = Modifier.height(30.dp))
        RecipeList()
    }

/*
    Text(
        text = "Food Bank",
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.safeNavigateTo(
                    FoodBankScreens.RecipeDetailsFromFoodBank.route
                        .replace(Constants.NavigationArguments.ID.addPathCurlyBrackets(), "11007")
                        .replace(
                            Constants.NavigationArguments.TYPE.addPathCurlyBrackets(),
                            RecipeCategories.Drink.name
                        )
                )
            },
        textAlign = TextAlign.Center
    )*/
}

@Preview(showBackground = true)
@Composable
fun PreviewFoodBankList() {
    DinnerRecommendationJetpackTheme {
        FoodBankList(rememberNavController())
    }
}
