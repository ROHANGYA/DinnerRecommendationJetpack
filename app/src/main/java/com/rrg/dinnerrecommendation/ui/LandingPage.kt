package com.rrg.dinnerrecommendation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@Composable
fun LandingPage() {
    Text(text = "recommendation", modifier = Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun PreviewLandingPage() {
    DinnerRecommendationJetpackTheme {
        LandingPage()
    }
}