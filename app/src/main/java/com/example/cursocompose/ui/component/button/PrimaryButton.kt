package com.example.cursocompose.ui.component.button

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cursocompose.R
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_LARGE
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_NONE
import com.example.cursocompose.ui.theme.eldarPayBlueGradientBrush
import com.example.cursocompose.ui.theme.eldarPayLightBlueSecondaryColor

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.button_placeholder),
    onClick: () -> Unit = { },
) {
    DefaultButton(
        text = text,
        modifier =
        modifier
            .padding(horizontal = PADDING_LARGE, vertical = PADDING_NONE),
        textColor = eldarPayLightBlueSecondaryColor,
        backgroundBrush = eldarPayBlueGradientBrush,
        onClick = onClick,
    )
}
