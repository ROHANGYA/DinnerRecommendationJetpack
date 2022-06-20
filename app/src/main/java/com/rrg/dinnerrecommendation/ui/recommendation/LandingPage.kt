package com.rrg.dinnerrecommendation.ui.recommendation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.models.keys.RecommendationScreens
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import com.rrg.dinnerrecommendation.ui.theme.poppinsFont
import com.rrg.dinnerrecommendation.utils.safeNavigateTo

@Composable
fun LandingPage(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4F),
                painter = painterResource(id = R.drawable.dinner_image),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                contentDescription = "HomeImage"
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = stringResource(id = R.string.welcome_to_dinner_generation),
            modifier = Modifier
                .fillMaxWidth(),
            fontFamily = poppinsFont,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.dinner_generation_steps_full),
            modifier = Modifier.fillMaxWidth(),
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(26.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                navController.safeNavigateTo(route = RecommendationScreens.MealCategories.route)
            },
            shape = RoundedCornerShape(18.dp)
        ) {
            Text(text = stringResource(id = R.string.start))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLandingPage() {
    DinnerRecommendationJetpackTheme {
        LandingPage(rememberNavController())
    }
}
