package com.example.cursocompose.ui.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cursocompose.R
import com.example.cursocompose.ui.main.MainScreens
import com.example.cursocompose.ui.theme.eldarLightBlueColor

@Composable
fun SplashEnd(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(eldarLightBlueColor),
        contentAlignment = Alignment.Center
    ) {
        var expanded by remember { mutableStateOf(false) }

        val expansion by animateFloatAsState(
            targetValue = if (expanded) 1f else 0.30f,
            animationSpec = tween(700, easing = LinearEasing),
            label = "Size",
            finishedListener = {
                navController.popBackStack()
                navController.navigate(MainScreens.Login.route)
            })

        LaunchedEffect(Unit) {
            expanded = !expanded
        }

        Image(
            painter = painterResource(R.drawable.logo_eldar_horizontal_full_color_border_with_legend),
            modifier = Modifier
                .size(230.dp, 70.dp)
                .drawWithContent {
                    clipRect(
                        right = size.width * expansion
                    ) {
                        this@drawWithContent.drawContent()
                    }
                },
            contentScale = ContentScale.Fit,
            contentDescription = "Eldar soluciones transaccionales"
        )
    }
}

