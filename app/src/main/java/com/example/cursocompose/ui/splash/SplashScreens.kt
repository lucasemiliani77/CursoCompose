package com.example.cursocompose.ui.splash

sealed class SplashScreens(val route: String) {

    data object SplashStart: SplashScreens("splash_start")

    data object SplashMiddle: SplashScreens("splash_middle")

    data object SplashEnd: SplashScreens("splash_end")

}