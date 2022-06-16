package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.ui.MainViewModel
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme
import kotlinx.coroutines.flow.collect

@Composable
fun TopBar(navController: NavHostController, mainViewModel: MainViewModel) {
    val defaultToolbarTitle = stringResource(id = R.string.dinner_recommendation)
    val toolbarTitle:MutableState<String> = remember {
        mutableStateOf(defaultToolbarTitle)
    }

    val toolbarBackAction:MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit){
        mainViewModel.mainEventChannel.collect{
            when(it){
                is MainViewModel.MainEvents.ToolbarEvents -> {
                    toolbarTitle.value = it.title
                    toolbarBackAction.value = it.backAction
                }
            }
        }
    }

    TopAppBar(
        title = {
            Text(
                text = toolbarTitle.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        },
        navigationIcon = {
            if(toolbarBackAction.value){
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(
                            id = R.string.back
                        )
                    )
                }
            }
        },
        elevation = 4.dp
    )
}

@Preview
@Composable
fun PreviewToolbar() {
    DinnerRecommendationJetpackTheme {
        TopBar(navController = rememberNavController(), mainViewModel = viewModel())
    }
}
