package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@Composable
fun FoodBankTab() {

    val selectedTabIndex: MutableState<Int> = remember {
        mutableStateOf(0)
    }

    TabRow(
        modifier = Modifier
            .height(28.dp),
        selectedTabIndex = selectedTabIndex.value,
    ) {
        Tab(
            selected = true,
            onClick = {
                selectedTabIndex.value = 0
                /*TODO*/
            },
            selectedContentColor = Color.White,
            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_restaurant),
                contentDescription = "meal"
            )
        }
        Tab(
            selected = false,
            onClick = {
                selectedTabIndex.value = 1
                /*TODO*/
            },
            selectedContentColor = Color.White,
            unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_wine_bar),
                contentDescription = "Drink"
            )
        }
    }
}

@Preview
@Composable
fun PreviewFoodBankSwitch() {
    DinnerRecommendationJetpackTheme {
        FoodBankTab()
    }
}
