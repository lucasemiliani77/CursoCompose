package com.example.cursocompose.ui.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.cursocompose.ui.resource.EldarFontSize.FONT_SIZE_BODY_EXTRA_SMALL
import com.example.cursocompose.ui.resource.EldarFontSize.FONT_SIZE_BODY_LARGE
import com.example.cursocompose.ui.resource.EldarFontSize.FONT_SIZE_BODY_MEDIUM
import com.example.cursocompose.ui.resource.EldarFontSize.FONT_SIZE_HEAD_LARGE
import com.example.cursocompose.ui.resource.futuraFontFamily
import com.example.cursocompose.ui.theme.eldarPayDarkTextColor
import com.example.cursocompose.ui.theme.eldarPayLightBlueColor
import com.example.cursocompose.ui.theme.getGradientForState

@Composable
fun NormalText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = FONT_SIZE_BODY_LARGE,
) = Text(
    fontFamily = futuraFontFamily,
    fontSize = fontSize,
    text = text,
    color = eldarPayDarkTextColor,
    textAlign = textAlign,
    modifier = modifier,
)

@Composable
fun BigText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = eldarPayDarkTextColor,
) = Text(
    fontFamily = futuraFontFamily,
    fontSize = FONT_SIZE_HEAD_LARGE,
    text = text,
    color = color,
    textAlign = textAlign,
    modifier = modifier,
)

@Composable
fun XBigText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight(700),
    fontSize: TextUnit = FONT_SIZE_HEAD_LARGE,
    color: Color = eldarPayLightBlueColor,
) = Text(
    fontFamily = futuraFontFamily,
    fontSize = fontSize,
    fontWeight = fontWeight,
    text = text,
    color = color,
    textAlign = textAlign,
    maxLines = 1,
    modifier = modifier,
)

@Composable
fun SmallText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = FONT_SIZE_BODY_MEDIUM,
    textAlign: TextAlign = TextAlign.Start,
) = Text(
    fontFamily = futuraFontFamily,
    fontSize = fontSize,
    text = text,
    color = eldarPayDarkTextColor,
    textAlign = textAlign,
    modifier = modifier,
)

@Composable
fun XSmallText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = FONT_SIZE_BODY_EXTRA_SMALL,
) = Text(
    fontFamily = futuraFontFamily,
    fontSize = fontSize,
    fontWeight = FontWeight(300),
    text = text,
    color = eldarPayLightBlueColor,
    textAlign = textAlign,
    modifier = modifier,
    softWrap = true,
)

@Composable
fun GrandientText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = eldarPayDarkTextColor,
    fontSize: TextUnit = FONT_SIZE_BODY_EXTRA_SMALL,
    fontWeight: FontWeight = FontWeight(400),
    fontFamily: FontFamily = futuraFontFamily,
    isPressed: Boolean = false,
    pressedGradientColors: List<Color>? = null,
) {
    val textBrush =
        if (pressedGradientColors != null) {
            getGradientForState(
                isPressed = isPressed,
                pressedColors = pressedGradientColors,
                unpressedColors = listOf(color, color),
            )
        } else {
            getGradientForState(
                isPressed = isPressed,
                pressedColors = listOf(color, color),
                unpressedColors = listOf(color, color),
            )
        }
    val textStyle =
        TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            brush = textBrush,
        )
    Text(
        text = text,
        textAlign = textAlign,
        modifier = modifier,
        style = textStyle,
    )
}