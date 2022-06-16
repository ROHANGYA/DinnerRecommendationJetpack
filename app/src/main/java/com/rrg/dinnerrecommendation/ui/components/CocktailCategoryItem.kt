package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.models.primary.CocktailCategory
import com.rrg.dinnerrecommendation.ui.recommendation.RecommendationViewModel
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont
import com.rrg.dinnerrecommendation.utils.addEmptyLines

@Composable
fun CocktailCategoryItem(
    item: CocktailCategory,
    viewModel: RecommendationViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                viewModel.selectedDrinkCategory.value = item
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(60.dp, 60.dp),
                painter = painterResource(id = R.drawable.ic_round_wine_bar),
                contentDescription = "cocktail",
                colorFilter = ColorFilter.tint(Color.LightGray),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.wrapContentSize(),
                text = item.strCategory.addEmptyLines(2),
                fontFamily = poppinsFont,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Clip
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCocktailCategoryItem() {
    DinnerRecommendationJetpackTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CocktailCategoryItem(
                CocktailCategory("Such a good drink category while also testing multi line hahah hohoh hihihi, is this correct?"),
                viewModel()
            )
        }
    }
}