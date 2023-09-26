package com.hazem.boruto.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
   val heroes=searchViewModel.searchedHeroes.collectAsLazyPagingItems()
    Scaffold(topBar = {
        SearchTopBar(text = searchQuery,
            onCloseClicked = { navController.popBackStack() },
            onSearchClicked = {
                              searchViewModel.searchHeroes(it)
            },
            onTextChange = { searchViewModel.updateSearchQuery(it) }
        )
    }) { padding ->

    }
}