package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.models.primary.MealCategory
import com.rrg.dinnerrecommendation.ui.recommendation.RecommendationViewModel
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont

@Composable
fun MealCategoryItem(
    item: MealCategory,
    viewModel: RecommendationViewModel
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 10.dp)
            .fillMaxWidth()
            .clickable { selectFoodCategory(viewModel, item) },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = colorResource(id = getCardColorState(viewModel, item))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.strCategoryThumb)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_placeholder_image),
                fallback = painterResource(R.drawable.ic_placeholder_image),
                contentDescription = stringResource(R.string.meal_categories),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth(0.4F)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = item.strCategory,
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1F, fill = true),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 2,
                fontFamily = poppinsFont,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                imageVector = Icons.Rounded.Info, contentDescription = "Category Info",
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(end = 4.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = { viewModel.currentlyViewingMealDetails.value = item }
                    )
            )
        }
    }
}

private fun selectFoodCategory(viewModel: RecommendationViewModel, currentItem: MealCategory) {
    if (viewModel.selectedMealCategory.value == currentItem) {
        viewModel.selectedMealCategory.value = null
    } else {
        viewModel.selectedMealCategory.value = currentItem
    }
}

private fun getCardColorState(viewModel: RecommendationViewModel, item: MealCategory): Int {
    return if (viewModel.selectedMealCategory.value == item) {
        R.color.oldLace_beige
    } else {
        R.color.white
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryRecord() {
    DinnerRecommendationJetpackTheme {
        MealCategoryItem(
            MealCategory(
                "test",
                "test",
                "https://www.themealdb.com/images/category/beef.png",
                "test"
            ),
            viewModel()
        )
    }
}
