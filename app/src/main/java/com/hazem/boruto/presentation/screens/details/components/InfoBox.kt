package com.hazem.boruto.presentation.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.hazem.boruto.R
import com.hazem.boruto.presentation.ui.theme.SMALL_PADDING
import com.hazem.boruto.presentation.ui.theme.titleColor

@Composable
fun InfoBox(
    icon: Painter,
    iconColor:Color,
    bigText: String,
    smallText: String,
    textColor: Color
) {
    Row (horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)){
        Icon(
            painter = icon,
            contentDescription = stringResource(R.string.info_icon),
            tint = iconColor
        )
        Column {
            Text(
                text = bigText,
                style = TextStyle(
                    color = textColor,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Black
                )
            )
            Text(
                modifier=Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                style = TextStyle(
                    color = textColor,
                    fontSize = MaterialTheme.typography.overline.fontSize
                )
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BoxInfoPreview(){
    InfoBox(icon = painterResource(R.drawable.bolt),iconColor=MaterialTheme.colors.primary, bigText = "92", smallText = "Power", textColor = MaterialTheme.colors.titleColor)
}