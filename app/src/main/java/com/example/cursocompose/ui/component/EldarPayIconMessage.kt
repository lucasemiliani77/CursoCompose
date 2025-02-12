package com.example.cursocompose.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cursocompose.R
import com.example.cursocompose.ui.component.text.SmallText
import com.example.cursocompose.ui.resource.EldarSizes.BUTTON_HEIGHT
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_MEDIUM
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_SMALL
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_TINY
import com.example.cursocompose.ui.theme.eldarPayDarkTextColor

@Preview(
    widthDp = 360,
    heightDp = 200,
    showBackground = true,
)
@Composable
fun ColumnScope.EldarPayIconMessage(
    modifier: Modifier = Modifier,
    msg: String = "Ingresar monto",
    @DrawableRes iconId: Int = R.drawable.icon_arrow_back
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = PADDING_MEDIUM, vertical = PADDING_SMALL)
            .align(Alignment.Start)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            modifier = Modifier.height(BUTTON_HEIGHT),
            tint = eldarPayDarkTextColor,
            contentDescription = "...",
        )

        SmallText(
            modifier = Modifier.padding(horizontal = PADDING_TINY),
            text = msg,
        )
    }
}