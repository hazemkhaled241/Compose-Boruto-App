package com.hazem.boruto.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.hazem.boruto.presentation.screens.home.components.HomeTopBar
import com.hazem.boruto.presentation.screens.home.components.RatingWidget
import com.hazem.boruto.presentation.ui.theme.LARGE_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen( homeViewModel: HomeViewModel= hiltViewModel()){
val allHeroes= homeViewModel.getAllHeroes.collectAsLazyPagingItems()
Scaffold(
    topBar = {
        HomeTopBar(onSearchClicked = {})
    }
) {
    Box(modifier = Modifier.padding(it)){
RatingWidget(modifier = Modifier.padding(all=LARGE_PADDING), rating = 7.5)
}
}
}