package com.rrg.dinnerrecommendation.ui.recommendation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.keys.RecipeCategories
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.models.primary.Drink
import com.rrg.dinnerrecommendation.models.primary.Meal
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar
import com.rrg.dinnerrecommendation.ui.components.GenericDinnerRecommendationItem
import com.rrg.dinnerrecommendation.ui.components.SuggestAnotherDinnerButton
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont
import com.rrg.dinnerrecommendation.utils.Constants
import com.rrg.dinnerrecommendation.utils.addPathCurlyBrackets
import com.rrg.dinnerrecommendation.utils.safeNavigateTo

@Composable
fun DinnerRecommendation(
    navController: NavHostController,
    viewModel: RecommendationViewModel
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(RecommendationViewModel.RecommendationEvents.GetDinnerRecommendation)
    }
    val recommendedMeal: MutableState<Meal?> = remember {
        mutableStateOf(null)
    }

    val recommendedDrink: MutableState<Drink?> = remember {
        mutableStateOf(null)
    }

    val isLoading: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }

    when (val state = viewModel.stateRecommendedMeal.value) {
        State.Loading -> {
            isLoading.value = true
        }
        is State.Loaded -> {
            isLoading.value = false
            recommendedMeal.value = state.data
        }
        is State.LoadingFailed -> {
            // TODO -- add error compose
        }
    }

    when (val state = viewModel.stateRecommendedDrink.value) {
        State.Loading -> {
            isLoading.value = true
        }
        is State.Loaded -> {
            isLoading.value = false
            recommendedDrink.value = state.data
        }
        is State.LoadingFailed -> {
            // TODO -- add error compose
        }
    }

    if (isLoading.value) {
        CircularIndeterminateProgressBar()
    } else {
        val meal = recommendedMeal.value
        val drink = recommendedDrink.value
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.your_dinner_is),
                    textAlign = TextAlign.Center,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 23.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                meal?.let {
                    GenericDinnerRecommendationItem(meal.strMeal, meal.strMealThumb) {
                        navController.safeNavigateTo(
                            RecommendationScreens.RecipeDetailsFromRecommendation.route
                                .replace(Constants.NavigationArguments.ID.addPathCurlyBrackets(), it.idMeal)
                                .replace(Constants.NavigationArguments.TYPE.addPathCurlyBrackets(), RecipeCategories.Meal.name)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                drink?.let {
                    GenericDinnerRecommendationItem(drink.strDrink, drink.strDrinkThumb) {
                        navController.safeNavigateTo(
                            RecommendationScreens.RecipeDetailsFromRecommendation.route
                                .replace(Constants.NavigationArguments.ID.addPathCurlyBrackets(), it.idDrink,)
                                .replace(Constants.NavigationArguments.TYPE.addPathCurlyBrackets(), RecipeCategories.Drink.name)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
                SuggestAnotherDinnerButton {
                    viewModel.onEvent(RecommendationViewModel.RecommendationEvents.SuggestAnotherDinner)
                }
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDinnerRecommendation() {
    DinnerRecommendationJetpackTheme {
        DinnerRecommendation(rememberNavController(), viewModel())
    }
}
