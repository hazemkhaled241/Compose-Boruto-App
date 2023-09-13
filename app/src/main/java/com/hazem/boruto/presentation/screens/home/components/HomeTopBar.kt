package com.hazem.boruto.presentation.screens.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hazem.boruto.R
import com.hazem.boruto.presentation.ui.theme.topAppBarBackgroundColor
import com.hazem.boruto.presentation.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.explore),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = Color.White
                )
            }
        }
    )
}
@Composable
@Preview
fun HomeTopBarPreview(){
    HomeTopBar {}
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun HomeTopBarDarkPreview(){
    HomeTopBar {}
}