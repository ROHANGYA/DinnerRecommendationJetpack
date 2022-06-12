package com.rrg.dinnerrecommendation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont

@Composable
fun CategoryRecord() {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 10.dp)
            .fillMaxWidth()
            .clickable {

            },
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "category Item",
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.Cyan, BlendMode.Difference)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "This is a sample category name which is a bit long and will eventually ellipsize",
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1F, fill = true),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontFamily = poppinsFont
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
                        onClick = {})
            )
        }
    }
}

@Composable
fun CategoryList() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            CategoryRecord()
            CategoryRecord()
            CategoryRecord()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryRecord() {
    DinnerRecommendationJetpackTheme {
        CategoryRecord()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryList() {
    DinnerRecommendationJetpackTheme {
        CategoryList()
    }
}