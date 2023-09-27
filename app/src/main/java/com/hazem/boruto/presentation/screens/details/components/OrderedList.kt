package com.hazem.boruto.presentation.screens.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.hazem.boruto.presentation.ui.theme.SMALL_PADDING

@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    textColor: Color
) {
    Column {
        Text(
            text = title,
            style = TextStyle(
                color = textColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        items.forEachIndexed { index, item ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index+1}. $item",
                style = TextStyle(
                    color = textColor,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                )
            )
        }
    }
}

@Preview()
@Composable
fun OrderedListPreview(){
    OrderedList(textColor= Color.White, title = "Family", items = listOf("Fugaku","Mikoto","Itachi","Sarada","Sakura"))
}