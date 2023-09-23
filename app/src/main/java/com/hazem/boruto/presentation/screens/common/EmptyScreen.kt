package com.hazem.boruto.presentation.screens.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.hazem.boruto.R
import com.hazem.boruto.presentation.ui.theme.DarkGray
import com.hazem.boruto.presentation.ui.theme.ERROR_IMAGE_SIZE
import com.hazem.boruto.presentation.ui.theme.LightGray
import com.hazem.boruto.presentation.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error) {
    val message by remember {
        mutableStateOf(parseErrorMessage(error))
    }
    val icon by remember {
        mutableIntStateOf(R.drawable.network_error)
    }
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        label = "",
        animationSpec = tween(1000)
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }
    EmptyContent(alphaAnim, icon, message)
}

@Composable
fun EmptyContent(alphaAnim: Float, icon: Int, message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(ERROR_IMAGE_SIZE)
                .alpha(alphaAnim),
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.error_image),
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray
        )
        Text(
            modifier = Modifier
                .padding(SMALL_PADDING)
                .alpha(alphaAnim),
            text = message,
            style = TextStyle(
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        )
    }
}

fun parseErrorMessage(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unavailable"
        }

        is ConnectException -> {
            "Internet Unavailable"
        }

        else -> {
            "Unknown Error."
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EmptyScreenPreview() {
    EmptyScreen(error = LoadState.Error(Throwable("SocketTimeoutException")))
}

@Composable
@Preview(showBackground = true)
fun EmptyScreenPreview2() {
    EmptyScreen(error = LoadState.Error(Throwable("ConnectionException")))
}