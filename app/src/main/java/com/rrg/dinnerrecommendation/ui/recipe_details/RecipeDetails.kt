package com.rrg.dinnerrecommendation.ui.recipe_details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.keys.RecipeCategories
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar
import com.rrg.dinnerrecommendation.ui.components.YoutubeButton
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont
import com.rrg.dinnerrecommendation.utils.showToast
import java.lang.Exception

@Composable
fun RecipeDetails(navController: NavHostController, idArgument: String, category: String) {
    val viewModel = hiltViewModel<RecipeDetailsViewModel>()
    LaunchedEffect(key1 = Unit) {
        when (category) {
            RecipeCategories.Meal.name -> {
                viewModel.onEvent(RecipeDetailsViewModel.RecipeDetailsEvents.FetchMealDetails(idArgument))
            }
            RecipeCategories.Drink.name -> {
                viewModel.onEvent(RecipeDetailsViewModel.RecipeDetailsEvents.FetchDrinkDetails(idArgument))
            }
        }
    }

    when (category) {
        RecipeCategories.Meal.name -> {
            when (val state = viewModel.mealDetails.value) {
                State.Loading -> {
                    CircularIndeterminateProgressBar()
                }
                is State.Loaded -> {
                    state.data.apply {
                        MainRecipeDetailsScreenContent(strInstructions, strYoutube)
                    }
                }
                is State.LoadingFailed -> {
                    TODO()
                }
            }
        }
        RecipeCategories.Drink.name -> {
            when (val state = viewModel.drinkDetails.value) {
                State.Loading -> {
                    CircularIndeterminateProgressBar()
                }
                is State.Loaded -> {
                    state.data.apply {
                        MainRecipeDetailsScreenContent(strInstructions, strVideo)
                    }
                }
                is State.LoadingFailed -> {
                    TODO()
                }
            }
        }
    }
}

@Composable
private fun MainRecipeDetailsScreenContent(recipe: String?, videoUrl: String?) {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text = stringResource(id = R.string.instructions),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp, bottom = 16.dp)
                    .padding(horizontal = 8.dp),
                fontFamily = poppinsFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
            YoutubeButton(videoUrl != null) {
                videoUrl?.let {
                    try {
                        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        context.startActivity(webIntent)
                    } catch (e: Exception) {
                        context.showToast(R.string.video_could_not_be_played)
                    }
                } ?: context.showToast(R.string.no_video_found)
            }
            Text(
                text = recipe ?: stringResource(id = R.string.no_recipe_found),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp, bottom = 16.dp)
                    .padding(horizontal = 20.dp),
                fontFamily = poppinsFont,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRecipeDetails() {
    DinnerRecommendationJetpackTheme {
        RecipeDetails(rememberNavController(), "test", "test")
    }
}
