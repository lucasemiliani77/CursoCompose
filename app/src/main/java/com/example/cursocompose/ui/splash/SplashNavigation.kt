package com.example.cursocompose.ui.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SplashNavigation(navController: NavHostController) {

    val splashNavController = rememberNavController()

    NavHost(navController = splashNavController, startDestination = SplashScreens.SplashStart.route) {

        composable(SplashScreens.SplashStart.route) {
            SplashStart(navController = splashNavController)
        }

        composable(SplashScreens.SplashMiddle.route) {
            SplashMiddle(navController = splashNavController)
        }

        composable(SplashScreens.SplashEnd.route) {
            SplashEnd(navController = navController)
        }

    }
}