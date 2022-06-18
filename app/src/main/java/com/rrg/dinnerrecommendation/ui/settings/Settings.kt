package com.rrg.dinnerrecommendation.ui.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SettingsPage() {
    Text(text = "Settings", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
}
