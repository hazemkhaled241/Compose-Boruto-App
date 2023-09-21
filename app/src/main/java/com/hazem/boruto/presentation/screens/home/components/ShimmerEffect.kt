package com.hazem.boruto.presentation.screens.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hazem.boruto.presentation.ui.theme.ABOUT_PLACE_HOLDER_HEIGHT
import com.hazem.boruto.presentation.ui.theme.EXTRA_SMALL_PADDING
import com.hazem.boruto.presentation.ui.theme.HERO_ITEM_HEIGHT
import com.hazem.boruto.presentation.ui.theme.LARGE_PADDING
import com.hazem.boruto.presentation.ui.theme.MEDIUM_PADDING
import com.hazem.boruto.presentation.ui.theme.NAME_PLACE_HOLDER_HEIGHT
import com.hazem.boruto.presentation.ui.theme.RATING_PLACE_HOLDER_HEIGHT
import com.hazem.boruto.presentation.ui.theme.SMALL_PADDING
import com.hazem.boruto.presentation.ui.theme.ShimmerDarkGray
import com.hazem.boruto.presentation.ui.theme.ShimmerLightGray
import com.hazem.boruto.presentation.ui.theme.ShimmerMediumGray

@Composable
fun ShimmerEffect() {
    LazyColumn(
        contentPadding = PaddingValues(SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        items(count = 2){
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem() {
    val transitions = rememberInfiniteTransition(label = "")
    val alphaAnim by transitions.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ), repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )
    ShimmerEffectItem(alpha = alphaAnim)

}

@Composable
fun ShimmerEffectItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(HERO_ITEM_HEIGHT),
        color = if (isSystemInDarkTheme()) Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(LARGE_PADDING)
    ) {
        Column(
            modifier = Modifier.padding(MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .alpha(alpha)
                    .height(NAME_PLACE_HOLDER_HEIGHT),
                color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(SMALL_PADDING)
            ) {}
            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
            repeat(3) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(alpha)
                        .height(ABOUT_PLACE_HOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(SMALL_PADDING)
                ) {}
                Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))

            }
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(5) {
                    Surface(
                        modifier = Modifier
                            .alpha(alpha)
                            .size(RATING_PLACE_HOLDER_HEIGHT),
                        color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                        shape = RoundedCornerShape(SMALL_PADDING)
                    ) {}
                    Spacer(modifier = Modifier.padding(all = SMALL_PADDING))

                }
            }

        }

    }
}

@Preview
@Composable
fun ShimmerItemPreview() {
    AnimatedShimmerItem()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShimmerItemDarkPreview() {
    AnimatedShimmerItem()
}