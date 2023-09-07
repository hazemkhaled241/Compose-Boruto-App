package com.hazem.boruto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hazem.boruto.presentation.navigation.SetUpNavGraph
import com.hazem.boruto.presentation.ui.theme.BorutoTheme


class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BorutoTheme {
                navController= rememberNavController()
                SetUpNavGraph(navController)
            }
        }
    }


}