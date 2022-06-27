package com.rrg.dinnerrecommendation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.models.keys.BottomBarScreens
import com.rrg.dinnerrecommendation.nav_graph.NavGraph
import com.rrg.dinnerrecommendation.ui.components.BottomNavBar
import com.rrg.dinnerrecommendation.ui.components.SplashScreen
import com.rrg.dinnerrecommendation.ui.components.TopBar
import com.rrg.dinnerrecommendation.ui.theme.DarkBeige
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.window.apply {
                statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.darkNavy_blue)
                navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.darkNavy_blue)
            }
            DinnerRecommendationJetpackTheme {
                navController = rememberNavController()

                val alphaAnim = animateFloatAsState(
                    targetValue =  1f ,
                    animationSpec = tween(
                        durationMillis = 3000
                    )
                )

                LaunchedEffect(key1 = Unit) {
                    delay(3000)
                    mainViewModel.showSplashScreen.value = false
                }

                if (mainViewModel.showSplashScreen.value) {
                    SplashScreen(alpha = alphaAnim.value)
                } else {
                    MainScreen(navController, mainViewModel)
                }
            }
        }
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.route) {
            BottomBarScreens.Recommendation.route, BottomBarScreens.FoodBank.route, BottomBarScreens.Settings.route -> {
                moveTaskToBack(true)
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    Scaffold(
        backgroundColor = DarkBeige,
        topBar = { TopBar(navController, mainViewModel) },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) { // To avoid content being hidden by scaffold slots
            NavGraph(navController, mainViewModel)
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DinnerRecommendationJetpackTheme {
        MainScreen(rememberNavController(), viewModel())
    }
}
