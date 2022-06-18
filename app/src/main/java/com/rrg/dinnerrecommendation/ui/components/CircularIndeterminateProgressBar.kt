package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.rrg.dinnerrecommendation.R

@Composable
fun CircularIndeterminateProgressBar(centered: Boolean = true) {
    if (centered) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CustomCircularProgressIndicators()
        }
    } else {
        CustomCircularProgressIndicators()
    }
}

@Composable
private fun CustomCircularProgressIndicators() {
    CircularProgressIndicator(
        modifier = Modifier.wrapContentSize(),
        color = colorResource(id = R.color.darkNavy_blue)
    )
}
