package com.rrg.dinnerrecommendation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.nav_graph.NavGraph
import com.rrg.dinnerrecommendation.ui.components.BottomBar
import com.rrg.dinnerrecommendation.ui.components.TopBar
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DinnerRecommendationJetpackTheme {
                val navController = rememberNavController()
                MainScreen(navController)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) { // To avoid content being hidden by scaffold slots
            NavGraph(navController = navController)
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DinnerRecommendationJetpackTheme {
        MainScreen(rememberNavController())
    }
}
