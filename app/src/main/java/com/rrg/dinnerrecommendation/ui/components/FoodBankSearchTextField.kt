package com.rrg.dinnerrecommendation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.rrg.dinnerrecommendation.R
import com.rrg.dinnerrecommendation.ui.theme.DarkNavyBlue
import com.rrg.dinnerrecommendation.ui.theme.Typography

@Composable
fun FoodBankSearchTextField(searchQuery: MutableState<String>, searchUpdate: (String) -> Unit) {
    val focusManager = LocalFocusManager.current
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        value = searchQuery.value,
        onValueChange = {
            searchQuery.value = it // searchUpdate(it) ->> for search as you type functionality
        },
        textStyle = Typography.body1,
        label = {
            Text(
                text = stringResource(id = R.string.search_recipes_here),
                color = colorResource(id = R.color.grey70)
            )
        },
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_search),
                contentDescription = "search"
            )
        },
        trailingIcon = {
            if (searchQuery.value.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable {
                        searchUpdate("")
                    },
                    painter = painterResource(id = R.drawable.ic_round_clear),
                    contentDescription = "clear Text"
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            backgroundColor = Color.White,
            leadingIconColor = DarkNavyBlue,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            cursorColor = DarkNavyBlue
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions {
            searchUpdate(searchQuery.value)
            focusManager.clearFocus()
        }
    )
}
