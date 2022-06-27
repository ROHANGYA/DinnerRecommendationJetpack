package com.rrg.dinnerrecommendation.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.ui.theme.DarkNavyBlue
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont
import com.rrg.dinnerrecommendation.utils.Constants

@Composable
fun SplashScreen(
    topDownOffset: Float = Constants.SplashScreen.END_POSITION,
    bottomUpOffset: Float = Constants.SplashScreen.END_POSITION,
    visibility: MutableState<Boolean>
) {
    AnimatedVisibility(
        modifier = Modifier
            .fillMaxSize(),
        visible = visibility.value,
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkNavyBlue),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkNavyBlue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(120.dp)
                        .offset(y = topDownOffset.dp),
                    painter = painterResource(id = R.drawable.ic_round_dining),
                    tint = colorResource(id = R.color.white),
                    contentDescription = "splash"
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .offset(y = bottomUpOffset.dp),
                    text = stringResource(id = R.string.dinner_recommendation),
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    DinnerRecommendationJetpackTheme {
        SplashScreen(0f, 0f, mutableStateOf(true))
    }
}
