package com.hazem.boruto.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hazem.boruto.presentation.screens.splash.SplashScreen
import com.hazem.boruto.presentation.screens.welcome.WelcomeScreen
import com.hazem.boruto.utils.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Welcome.route){
        composable(route=Screen.Splash.route){
            SplashScreen(navController)
        }
        composable(route=Screen.Home.route){}
        composable(route=Screen.Details.route, arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY){
            type= NavType.IntType
        })){

        }
        composable(route=Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(route=Screen.Search.route){}
    }
}