package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.R

@Composable
fun YoutubeButton(enabled: Boolean = true, onClick: () -> Unit) {
    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        enabled = enabled,
        elevation = ButtonDefaults.elevation(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.youtube_red))
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.watch_on_youtube),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 1.dp, end = 8.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_round_video),
                contentDescription = "Youtube",
                tint = Color.White
            )
        }
    }
}
