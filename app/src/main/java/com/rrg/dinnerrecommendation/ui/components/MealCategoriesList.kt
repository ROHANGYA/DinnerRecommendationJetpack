package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@Composable
fun MealCategoriesList(
    data: List<MealCategory>,
    onNextClick: () -> Unit,
    onSelected: () -> Unit
) {

    // TODO -- NO data

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(data) { item ->
            MealCategoryItem(item, onSelected)
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
    NextButtonFromCategories(onNextClick)
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoriesList() {
    DinnerRecommendationJetpackTheme {
        MealCategoriesList(
            listOf(),
            {},
            {}
        )
    }
}
