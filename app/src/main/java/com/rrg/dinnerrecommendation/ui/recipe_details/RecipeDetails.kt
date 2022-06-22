package com.rrg.dinnerrecommendation.ui.recipe_details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.keys.RecipeCategories
import com.rrg.dinnerrecommendation.ui.MainViewModel
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar
import com.rrg.dinnerrecommendation.ui.components.GenericColumnTagItem
import com.rrg.dinnerrecommendation.ui.components.YoutubeButton
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont
import com.rrg.dinnerrecommendation.utils.showToast

@Composable
fun RecipeDetails(
    mainViewModel: MainViewModel,
    viewModel: RecipeDetailsViewModel,
    idArgument: String,
    category: String
) {

    val id: MutableState<String> = remember {
        mutableStateOf(idArgument)
    }

    val recipeCategory: MutableState<String> = remember {
        mutableStateOf(category)
    }

    LaunchedEffect(key1 = Unit) {
        when (recipeCategory.value) {
            RecipeCategories.Meal.name -> {
                viewModel.onEvent(
                    RecipeDetailsViewModel.RecipeDetailsEvents.FetchMealDetails(id.value)
                )
            }
            RecipeCategories.Drink.name -> {
                viewModel.onEvent(
                    RecipeDetailsViewModel.RecipeDetailsEvents.FetchDrinkDetails(id.value)
                )
            }
        }
    }

    when (recipeCategory.value) {
        RecipeCategories.Meal.name -> {
            when (val state = viewModel.mealDetails.value) {
                State.Loading -> {
                    CircularIndeterminateProgressBar()
                }
                is State.Loaded -> {
                    state.data.apply {
                        mainViewModel.updateToolbar(strMeal, true)
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
                        mainViewModel.updateToolbar(strDrink, true)
                        MainRecipeDetailsScreenContent(
                            strInstructions, strVideo,
                            strAlcoholic == stringResource(id = R.string.alcoholic)
                        )
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
private fun MainRecipeDetailsScreenContent(
    recipe: String?,
    videoUrl: String?,
    isAlcoholic: Boolean = false
) {
    val context = LocalContext.current
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
    ) {

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
        if (isAlcoholic) {
            GenericColumnTagItem(text = stringResource(id = R.string.alcoholic), yOffset = (+12).dp)
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

@Preview(showBackground = true)
@Composable
private fun PreviewRecipeDetails() {
    DinnerRecommendationJetpackTheme {
        RecipeDetails(viewModel(), viewModel(), "test", "test")
    }
}
