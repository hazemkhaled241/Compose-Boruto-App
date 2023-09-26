package com.hazem.boruto.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.hazem.boruto.presentation.screens.common.EmptyScreen
import com.hazem.boruto.presentation.screens.common.ListContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    var isFirstTime by remember {
        mutableStateOf(true)
    }
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()
    Scaffold(topBar = {
        SearchTopBar(text = searchQuery,
            onCloseClicked = { navController.popBackStack() },
            onSearchClicked = {
                searchViewModel.searchHeroes(it)
                isFirstTime = false
            },
            onTextChange = { searchViewModel.updateSearchQuery(it) }
        )
    }, content = {
        if(!isFirstTime)
         ListContent(heroes = heroes, navController = navController)
        else
            EmptyScreen()
    })
}