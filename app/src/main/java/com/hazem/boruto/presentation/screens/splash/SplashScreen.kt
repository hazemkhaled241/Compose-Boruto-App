package com.hazem.boruto.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.hazem.boruto.R
import com.hazem.boruto.presentation.ui.theme.purple500
import com.hazem.boruto.presentation.ui.theme.purple700

@Composable
fun SplashScreen(navController: NavHostController) {
    Splash()
}

@Composable
fun Splash() {
    if(isSystemInDarkTheme()){
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            Image(modifier = Modifier.align(Alignment.Center), painter = painterResource(id = R.drawable.logo), contentDescription = stringResource(R.string.app_logo) )
        }
    }
    else{
    Box(
        modifier = Modifier
            .background(Brush.verticalGradient(listOf(purple700, purple500)))
            .fillMaxSize()
    ) {
        Image(modifier = Modifier.align(Alignment.Center), painter = painterResource(id = R.drawable.logo), contentDescription = stringResource(R.string.app_logo) )
    }
    }
}
@Composable
@Preview
fun SplashPreview(){
    Splash()
}