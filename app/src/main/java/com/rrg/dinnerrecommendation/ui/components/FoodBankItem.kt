package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont

@Composable
fun FoodBankItem(name: String, imageUrl: String, tagList: List<String?> = listOf(), onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 8.dp).padding(top = 14.dp)
            .clickable {
                onClick.invoke()
            },
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "image",
                placeholder = painterResource(id = R.drawable.ic_placeholder_image),
                fallback = painterResource(id = R.drawable.ic_placeholder_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
                fontFamily = poppinsFont,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 10.dp)
            ) {
                tagList.filterNotNull().forEach {
                    GenericRowTagItem(
                        text = it,
                        tagColor = colorResource(id = R.color.darkNavy_blue)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFoodBankItem() {
    DinnerRecommendationJetpackTheme {
        FoodBankItem(
            "This is a meal or a drink or whatever",
            "TEST",
            listOf("test", "More Testing", "haha", "hihi")
        ) { }
    }
}
