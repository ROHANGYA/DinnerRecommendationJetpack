package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColumnScope.GenericTagItem(
    text: String,
    alignmentHorizontal: Alignment.Horizontal = Alignment.CenterHorizontally,
    textColor: Color = Color.White,
    tagColor: Color = Color.Red
) {
    Box(
        modifier = Modifier
            .offset(y = (+12).dp)
            .wrapContentSize()
            .background(
                tagColor,
                RoundedCornerShape(6.dp)
            )
            .align(alignmentHorizontal)
    ) {
        Text(
            text = text,
            Modifier
                .wrapContentWidth()
                .padding(horizontal = 3.dp),
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
