package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.ui.theme.DinnerRecommendationJetpackTheme

@Composable
fun SuggestAnotherDinnerButton(
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(text = stringResource(id = R.string.suggest_another_dinner))
    }
}

@Preview
@Composable
private fun PreviewButton() {
    DinnerRecommendationJetpackTheme {
        SuggestAnotherDinnerButton {}
    }
}
