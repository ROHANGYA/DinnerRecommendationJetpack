package com.rrg.dinnerrecommendation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.models.BottomBarActions
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@Composable
fun LandingPage() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "welcome to the jungle where stuff gets crazy",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.back
                            )
                        )
                    }
                },
                elevation = 4.dp
            )
        },
        bottomBar = {
            BottomNavigation(
                elevation = 4.dp
            ) {
                BottomBarActions.values().forEach {
                    BottomNavigationItem(
                        selected = true,
                        onClick = { /*TODO*/ },
                        label = {
                            Text(text = stringResource(id = it.title))
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = it.icon),
                                contentDescription = stringResource(
                                    id = R.string.back
                                )
                            )

                        })
                }
            }
        }
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLandingPage() {
    DinnerRecommendationJetpackTheme {
        LandingPage()
    }
}