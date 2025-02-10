package com.example.cursocompose.ui.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cursocompose.R
import com.example.cursocompose.ui.theme.eldarLightBlueColor

@Composable
fun SplashMiddle(navController: NavController) {
    Box(
    modifier = Modifier
        .background(eldarLightBlueColor)
        .fillMaxSize(),
    contentAlignment = Alignment.Center
) {
    var displaced by remember { mutableStateOf(false) }

    val displacement by animateFloatAsState(
        targetValue = if (displaced) -83f else 0f,
        animationSpec = tween(600, easing = LinearEasing),
        label = "Size",
        finishedListener = { navController.navigate(SplashScreens.SplashEnd.route) })

    Image(
        modifier = Modifier
            .offset(x = displacement.dp)
            .size(64.dp, 70.dp),
        painter = painterResource(R.drawable.ic_eldar),
        contentDescription = "Eldar soluciones transaccionales"
    )

    LaunchedEffect(Unit) {
        displaced = !displaced
    }
}}