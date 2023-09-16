package com.hazem.boruto.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val purple200 = Color(0xFFBB86FC)
val purple500 = Color(0xFF6200EE)
val purple700 = Color(0xFF3700B3)
val Teal200 = Color(0XFF03DAC5)
val StarColor=Color(0XFFFFC94D)


val LightGray = Color(0XFFD8D8D8)
val DarkGray = Color(0XFF2A2A2A)

val Colors.welcomeScreenBackGroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black  else Color.White

val Colors.titleColor
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else DarkGray

val Colors.descriptionColor
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray.copy(alpha = .5f) else DarkGray.copy(alpha = .5f)

val Colors.topAppBarContentColor:Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else Color.White

val Colors.topAppBarBackgroundColor:Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else purple500


