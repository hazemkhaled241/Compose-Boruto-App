package com.hazem.boruto.presentation.screens.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.rememberAsyncImagePainter
import com.hazem.boruto.R
import com.hazem.boruto.domain.model.Hero
import com.hazem.boruto.presentation.navigation.Screen
import com.hazem.boruto.presentation.screens.home.components.RatingWidget
import com.hazem.boruto.presentation.screens.home.components.ShimmerEffect
import com.hazem.boruto.presentation.ui.theme.HERO_ITEM_HEIGHT
import com.hazem.boruto.presentation.ui.theme.LARGE_PADDING
import com.hazem.boruto.presentation.ui.theme.MEDIUM_PADDING
import com.hazem.boruto.presentation.ui.theme.SMALL_PADDING
import com.hazem.boruto.presentation.ui.theme.topAppBarContentColor
import com.hazem.boruto.utils.Constants.BASE_URL

@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>, navController: NavHostController
) {
    val result = handlePagingReturn(heroes = heroes)
    if (result){
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(
                SMALL_PADDING
            )
        ) {
            items(
                count = heroes.itemCount,
                key = heroes.itemKey { it.id },
            ) { index ->
                val hero = heroes[index]
                hero?.let {
                    HeroItem(hero = it, navController = navController)
                }
            }
        }
}
}

@Composable
fun handlePagingReturn(
    heroes: LazyPagingItems<Hero>
): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }
        return when{
            loadState.refresh is LoadState.Loading ->{
                ShimmerEffect()
                false
            }
            error!=null->{
                false
            }

            else -> true
        }
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    navController: NavHostController
) {
    val imagePainter = rememberAsyncImagePainter(
        model = "$BASE_URL${hero.image}", placeholder = painterResource(
            R.drawable.placeholder
        ), error = painterResource(R.drawable.placeholder)
    )

    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable { navController.navigate(Screen.Details.passHeroId(heroId = hero.id)) },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(LARGE_PADDING)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = imagePainter,
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(bottomStart = LARGE_PADDING, bottomEnd = LARGE_PADDING)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MEDIUM_PADDING)
            ) {
                Text(
                    text = hero.name,
                    style = TextStyle(
                        color = MaterialTheme.colors.topAppBarContentColor,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                    ), maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = hero.about,
                    style = TextStyle(
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    ), maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating
                    )
                    Text(
                        text = "(${hero.rating})",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            color = Color.White.copy(alpha = ContentAlpha.medium)
                        )
                    )
                }
            }

        }
    }

}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun HeroItemPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Sasuke",
            image = "",
            about = "Some thing random",
            rating = 4.5,
            power = 100,
            month = "",
            day = "",
            family = emptyList(),
            abilities = emptyList(),
            natureTypes = emptyList()
        ), navController = rememberNavController()
    )
}