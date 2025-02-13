package com.example.cursocompose.ui.component.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cursocompose.R
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_LARGE
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_NONE
import com.example.cursocompose.ui.resource.mediumRoundedCornerShape
import com.example.cursocompose.ui.theme.eldarPayDarkTextColor
import com.example.cursocompose.ui.theme.eldarPayLightBlueColor

@Preview(
    widthDp = 360,
    heightDp = 100,
    showBackground = true,
)
@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.button_placeholder),
    onClick: () -> Unit = { },
) {
    DefaultButton(
        text = text,
        textColor = eldarPayDarkTextColor,
        backgroundColor = eldarPayLightBlueColor,
        modifier =
        modifier
            .padding(horizontal = PADDING_LARGE, vertical = PADDING_NONE)
            .border(0.5.dp, eldarPayLightBlueColor, mediumRoundedCornerShape),
        onClick = onClick,
    )
}
