package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.models.primary.CocktailCategory
import com.rrg.dinnerrecommendation.ui.recommendation.RecommendationViewModel
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@ExperimentalFoundationApi
@Composable
fun CocktailCategoryList(
    data: List<CocktailCategory>,
    onNextClick: () -> Unit,
    viewModel: RecommendationViewModel
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(bottom = 50.dp),
        cells = GridCells.Fixed(3)
    ) {
        items(data) { item ->
            CocktailCategoryItem(item, viewModel)
        }
        /* TODO -- find solution to append full span item to grid list
        item(span = {
            GridItemSpan(3)
        }) {
            Spacer(modifier = Modifier.height(50.dp))
        }*/
    }
    NextButtonFromCategories(onNextClick, RecommendationScreens.CocktailCategories)
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun PreviewCocktailCategoryList() {
    DinnerRecommendationJetpackTheme {
        CocktailCategoryList(
            listOf(
                CocktailCategory("Coffee"),
                CocktailCategory("Tea"),
                CocktailCategory("Alcoholic"),
                CocktailCategory("Testing"),
                CocktailCategory("More Testing")
            ),
            {}, viewModel()
            )
        }
    }
    