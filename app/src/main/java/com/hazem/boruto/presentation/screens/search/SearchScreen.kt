package com.hazem.boruto.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen() {
    Scaffold(topBar = {
        SearchTopBar(text = "", onCloseClicked = {}, onSearchClicked = {}, onTextChange = {})
    }) {  padding ->

    }
}