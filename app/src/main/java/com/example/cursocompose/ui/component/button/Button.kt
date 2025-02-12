package com.example.cursocompose.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.cursocompose.R
import com.example.cursocompose.ui.resource.EldarFontSize.FONT_SIZE_BODY_MEDIUM
import com.example.cursocompose.ui.resource.EldarSizes.BUTTON_HEIGHT
import com.example.cursocompose.ui.resource.futuraFontFamily
import com.example.cursocompose.ui.resource.mediumRoundedCornerShape
import com.example.cursocompose.ui.theme.eldarPayLightBlueSecondaryColor

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.button_placeholder),
    textColor: Color = eldarPayLightBlueSecondaryColor,
    backgroundBrush: Brush? = null,
    backgroundColor: Color? = null,
    border: Boolean = false,
    onClick: () -> Unit = { },
) {
    Box(
        modifier =
        modifier
            .let {
                when {
                    backgroundBrush != null ->
                        modifier.background(
                            backgroundBrush,
                            mediumRoundedCornerShape,
                        )

                    backgroundColor != null ->
                        modifier.background(
                            backgroundColor,
                            mediumRoundedCornerShape,
                        )

                    else -> modifier
                }
            }.height(BUTTON_HEIGHT)
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Text(
            text = text,
            color = textColor,
            fontFamily = futuraFontFamily,
            fontSize = FONT_SIZE_BODY_MEDIUM,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
