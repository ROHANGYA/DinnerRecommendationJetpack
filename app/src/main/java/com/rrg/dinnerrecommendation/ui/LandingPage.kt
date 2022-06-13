package com.rrg.dinnerrecommendation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont

@Composable
fun LandingPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.welcome_to_dinner_generation),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F, true),
            fontFamily = poppinsFont,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .weight(1F, true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.dinner_generation_steps_label),
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = stringResource(id = R.string.dinner_generation_steps_1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
                Text(
                    text = stringResource(id = R.string.dinner_generation_steps_2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.dinner_generation_steps_3),
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .weight(1F, true),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(text = stringResource(id = R.string.start))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLandingPage() {
    DinnerRecommendationJetpackTheme {
        LandingPage()
    }
}
