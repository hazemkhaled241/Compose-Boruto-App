package com.hazem.boruto.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.hazem.boruto.R
import com.hazem.boruto.domain.model.OnBoardingPage
import com.hazem.boruto.presentation.ui.theme.DarkGray
import com.hazem.boruto.presentation.ui.theme.EXTRA_LARGE_PADDING
import com.hazem.boruto.presentation.ui.theme.FINISH_BUTTON_WIDTH
import com.hazem.boruto.presentation.ui.theme.LARGE_PADDING
import com.hazem.boruto.presentation.ui.theme.LightGray
import com.hazem.boruto.presentation.ui.theme.PAGER_INDICATOR_HEIGHT
import com.hazem.boruto.presentation.ui.theme.SMALL_LARGE_PADDING
import com.hazem.boruto.presentation.ui.theme.VERY_SMALL_LARGE_PADDING
import com.hazem.boruto.presentation.ui.theme.descriptionColor
import com.hazem.boruto.presentation.ui.theme.purple700
import com.hazem.boruto.presentation.ui.theme.titleColor
import com.hazem.boruto.presentation.ui.theme.welcomeScreenBackGroundColor
import com.hazem.boruto.utils.Constants.ON_BOARDING_SCREEN_COUNT

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(navController: NavHostController) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        ON_BOARDING_SCREEN_COUNT
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackGroundColor)
    ) {
        HorizontalPager(
            state = pagerState,
        ) { pageIndex ->
            PagerScreen(onBoardingPage = pages[pageIndex])
        }
        PagerIndicator(pagerState.currentPage)
        FinishButton(pagerState.currentPage) {

        }

    }

}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .fillMaxHeight(.6f), painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image)
        )
        Text(
            text = onBoardingPage.title,
            style = TextStyle(
                color = MaterialTheme.colors.titleColor,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_LARGE_PADDING),
            text = onBoardingPage.description,
            style = TextStyle(
                color = MaterialTheme.colors.descriptionColor,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

        )
    }
}

@Composable
fun PagerIndicator(currentPage: Int) {
    Row(
        Modifier
            .height(PAGER_INDICATOR_HEIGHT)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(ON_BOARDING_SCREEN_COUNT) { iteration ->
            val color =
                if (isSystemInDarkTheme()) if (currentPage == iteration) purple700 else DarkGray
                else if (currentPage == iteration) purple700 else LightGray
            Box(
                modifier = Modifier
                    .padding(VERY_SMALL_LARGE_PADDING)
                    .clip(CircleShape)
                    .background(color)
                    .size(SMALL_LARGE_PADDING)

            )
        }
    }
}
@Composable
fun FinishButton(currentPage: Int,onClick:()->Unit) {
Row (modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center){
    AnimatedVisibility(visible = currentPage+1==ON_BOARDING_SCREEN_COUNT,modifier = Modifier.width(FINISH_BUTTON_WIDTH)) {
        Button(onClick = onClick, modifier = Modifier.padding(vertical = LARGE_PADDING)) {
            Text(text = stringResource(R.string.finish), style = TextStyle(color = Color.White))
        }
    }

}
}