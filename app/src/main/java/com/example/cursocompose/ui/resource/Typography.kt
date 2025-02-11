package com.example.cursocompose.ui.resource

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.cursocompose.R

private val lightFont = Font(R.font.futura45light, FontWeight.Light, FontStyle.Normal)
private val lightItalicFont = Font(R.font.futura46lightitalic, FontWeight.Light, FontStyle.Italic)
private val regularFont = Font(R.font.futura55regular, FontWeight.Normal, FontStyle.Normal)
private val italicFont = Font(R.font.futura56italic, FontWeight.Normal, FontStyle.Italic)
private val mediumFont = Font(R.font.futura65medium, FontWeight.Medium, FontStyle.Normal)
private val mediumItalicFont = Font(R.font.futura66mediumitalic, FontWeight.Medium, FontStyle.Italic)
private val boldFont = Font(R.font.futura75bold, FontWeight.Bold, FontStyle.Normal)
private val boldItalicFont = Font(R.font.futura76bolditalic, FontWeight.Bold, FontStyle.Italic)

// Create a font family to use in TextStyles
val futuraFontFamily =
    FontFamily(
        lightFont,
        lightItalicFont,
        regularFont,
        italicFont,
        mediumFont,
        mediumItalicFont,
        boldFont,
        boldItalicFont,
    )