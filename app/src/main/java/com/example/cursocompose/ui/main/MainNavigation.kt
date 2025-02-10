package com.example.cursocompose.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cursocompose.ui.login.LoginScreen
import com.example.cursocompose.ui.splash.SplashNavigation

@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = MainScreens.SplashNavigation.route){

        composable(MainScreens.SplashNavigation.route) {
            SplashNavigation(navController = navController)
        }

        composable(MainScreens.Login.route) {
            LoginScreen(navController = navController)
        }

    }

}