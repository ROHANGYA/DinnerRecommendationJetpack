package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens

@Composable
fun NextButtonFromCategories(
    onNextClick: () -> Unit,
    recommendationScreens: RecommendationScreens
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            onClick = { onNextClick.invoke() },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = stringResource(
                    id = if (recommendationScreens == RecommendationScreens.CocktailCategories) {
                        R.string.recommend_a_dinner
                    } else {
                        R.string.next
                    }
                )
            )
        }
    }
}
