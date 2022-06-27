package com.rrg.dinnerrecommendation.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.BuildConfig
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.ui.theme.DarkNavyBlue
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont

@Composable
fun SettingsPage() {

    val headerModifier = Modifier
        .fillMaxWidth(0.8f)
        .background(DarkNavyBlue, RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = headerModifier
        ) {
            Text(
                text = stringResource(id = R.string.attribution),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 8.dp),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontFamily = poppinsFont,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Text(
            text = stringResource(id = R.string.attribution_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 20.dp),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = headerModifier
        ) {
            Text(
                text = stringResource(id = R.string.version),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 8.dp),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontFamily = poppinsFont,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Text(
            text = "v${BuildConfig.VERSION_NAME}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 20.dp),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettings() {
    DinnerRecommendationJetpackTheme {
        SettingsPage()
    }
}
