package com.rrg.dinnerrecommendation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@Composable
fun CategoriesList() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            for (i in 1..15) {
                CategoryRecord()
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoriesList() {
    DinnerRecommendationJetpackTheme {
        CategoriesList()
    }
}