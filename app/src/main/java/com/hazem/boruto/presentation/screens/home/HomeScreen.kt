package com.hazem.boruto.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.hazem.boruto.presentation.screens.home.components.HomeTopBar

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

}
}