package com.rrg.dinnerrecommendation.ui.recipe_details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.core.State
import com.rrg.dinnerrecommendation.models.keys.RecipeCategories
import com.rrg.dinnerrecommendation.ui.MainViewModel
import com.rrg.dinnerrecommendation.ui.components.CircularIndeterminateProgressBar
import com.rrg.dinnerrecommendation.ui.components.GenericColumnTagItem
import com.rrg.dinnerrecommendation.ui.components.GenericError
import com.rrg.dinnerrecommendation.ui.components.YoutubeButton
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont
import com.rrg.dinnerrecommendation.utils.Constants.Preview.Companion.WHITE_ARGB
import com.rrg.dinnerrecommendation.utils.showToast
import kotlin.math.abs

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
        load(recipeCategory, viewModel, id)
    }

    when (recipeCategory.value) {
        RecipeCategories.Meal.name -> {
            when (val state = viewModel.mealDetails.value) {
                State.Loading -> {
                    CircularIndeterminateProgressBar()
                }
                is State.Loaded -> {
                    state.data?.apply {
                        mainViewModel.updateToolbar(strMeal, true)
                        MainRecipeDetailsScreenContent(
                            recipe = strInstructions,
                            videoUrl = strYoutube,
                            imageUrl = strMealThumb
                        )
                    }
                }
                is State.LoadingFailed -> {
                    GenericError {
                        load(recipeCategory, viewModel, id)
                    }
                }
                is State.RetryLoading -> {
                    GenericError(isLoading = true) { }
                }
            }
        }
        RecipeCategories.Drink.name -> {
            when (val state = viewModel.drinkDetails.value) {
                State.Loading -> {
                    CircularIndeterminateProgressBar()
                }
                is State.Loaded -> {
                    state.data?.apply {
                        mainViewModel.updateToolbar(strDrink, true)
                        MainRecipeDetailsScreenContent(
                            recipe = strInstructions,
                            videoUrl = strVideo,
                            isAlcoholic = strAlcoholic == stringResource(id = R.string.alcoholic),
                            imageUrl = strDrinkThumb
                        )
                    }
                }
                is State.LoadingFailed -> {
                    GenericError {
                        load(recipeCategory, viewModel, id)
                    }
                }
                is State.RetryLoading -> {
                    GenericError(isLoading = true) { }
                }
            }
        }
    }
}

private fun load(
    category: MutableState<String>,
    viewModel: RecipeDetailsViewModel,
    id: MutableState<String>
) {
    when (category.value) {
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

@Composable
private fun MainRecipeDetailsScreenContent(
    recipe: String?,
    videoUrl: String?,
    isAlcoholic: Boolean = false,
    imageUrl: String
) {
    val context = LocalContext.current
    val scroll = rememberScrollState()
    val imageHeight = 320.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "image",
            placeholder = painterResource(id = R.drawable.ic_placeholder_image),
            fallback = painterResource(id = R.drawable.ic_placeholder_image),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(imageHeight)
                .wrapContentWidth()
                .clip(RoundedCornerShape(8.dp))
                .graphicsLayer {
                    /**
                     * Scroll value increments as we scroll (from 0 to XXX)
                     * 1. we need to divide by a multiplier of 10 to move decimals,
                     *          e.g scroll of 50 becomes 0.5 when divided by 100
                     *              *** higher the divisor, the slower the transition ***
                     * 2. We limit the range to 0..1 as that is what alpha range is ...
                     *          or whatever range within 0..1 we want to stop
                     * 3. We subtract by max value (1) to inverse the scroll progress,
                     *       as the normal scroll value increments (0...upwards)
                     *       which will make our composable invisible at first then visible
                     *       as we scroll (which we don't want)
                     * 4. Take absolute value to ignore negative sign
                     */
                    alpha = abs((scroll.value.toFloat() / 1000).coerceIn(0F, 0.8F) - 1)
                }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.height(imageHeight))
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

@Preview(backgroundColor = WHITE_ARGB, showBackground = true)
@Composable
private fun PreviewRecipeDetails() {
    DinnerRecommendationJetpackTheme {
        MainRecipeDetailsScreenContent("Test", "Test", true, "test")
    }
}
