package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.models.primary.CocktailCategory
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@ExperimentalFoundationApi
@Composable
fun CocktailCategoryList(
    data: List<CocktailCategory>,
    onSelected: () -> Unit,
    onNextClick: () -> Unit
) {
    LazyVerticalGrid(cells = GridCells.Adaptive(100.dp)) {
        items(data) { item ->
            CocktailCategoryItem(item = item, onSelected = onSelected)
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
    NextButtonFromCategories(onNextClick)
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
                CocktailCategory("More Testing"),
                CocktailCategory("Ass Drinks")
            ),
            {}, {}
        )
    }
}
