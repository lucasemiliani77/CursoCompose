package com.example.cursocompose.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun getGradientForState(
    isPressed: Boolean,
    pressedColors: List<Color>,
    unpressedColors: List<Color>,
): Brush {
    val colors = if (isPressed) pressedColors else unpressedColors
    return Brush.verticalGradient(colors)
}