package com.example.cursocompose.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.cursocompose.ui.theme.EldarPayTheme

@Composable
fun EldarPayApp() {

    EldarPayTheme {
        val navController = rememberNavController()

        MainNavigation(navController)
    }
}