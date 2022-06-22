package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColumnScope.GenericColumnTagItem(
    text: String,
    alignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    textColor: Color = Color.White,
    tagColor: Color = Color.Red,
    xOffset: Dp = (+0).dp,
    yOffset:Dp = (+0).dp
) {
    Box(
        modifier = Modifier
            .offset(xOffset,yOffset)
            .wrapContentSize()
            .background(
                tagColor,
                RoundedCornerShape(6.dp)
            )
            .align(alignment)
    ) {
        Text(
            text = text,
            Modifier
                .wrapContentWidth()
                .padding(horizontal = 6.dp),
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun RowScope.GenericRowTagItem(
    text: String,
    alignment: Alignment.Vertical = Alignment.CenterVertically,
    textColor: Color = Color.White,
    tagColor: Color = Color.Red,
    xOffset: Dp = (+0).dp,
    yOffset:Dp = (+0).dp
) {
    Box(
        modifier = Modifier
            .offset(xOffset,yOffset)
            .wrapContentSize()
            .background(
                tagColor,
                RoundedCornerShape(6.dp)
            )
            .align(alignment)
    ) {
        Text(
            text = text,
            Modifier
                .wrapContentWidth()
                .padding(horizontal = 6.dp),
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
