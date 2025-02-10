package com.example.cursocompose.ui.main

sealed class MainScreens(val route: String) {

    data object SplashNavigation: MainScreens("splash_navigation")

    data object Login: MainScreens("login")

    data object Menu: MainScreens("menu")

}