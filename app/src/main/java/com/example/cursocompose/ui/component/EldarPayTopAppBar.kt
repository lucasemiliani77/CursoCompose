package com.example.cursocompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cursocompose.R
import com.example.cursocompose.ui.resource.EldarFontSize.FONT_SIZE_BODY_MEDIUM
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_LARGE
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_MEDIUM
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_SMALL
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_TINY
import com.example.cursocompose.ui.resource.futuraFontFamily
import com.example.cursocompose.ui.resource.mediumRoundedCornerShape
import com.example.cursocompose.ui.theme.eldarPayLightBlueCardColor
import com.example.cursocompose.ui.theme.eldarPayLightBlueColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EldarPayTopAppBar(onClick: () -> Unit = { }) {
    TopAppBar(
        modifier =
        Modifier
            .padding(horizontal = PADDING_MEDIUM, vertical = PADDING_TINY)
            .clip(mediumRoundedCornerShape)
            .background(Color.White),
        colors =
        TopAppBarDefaults.topAppBarColors(
            containerColor = eldarPayLightBlueColor,
            titleContentColor = eldarPayLightBlueColor,
        ),
        title = {
            Image(
                painterResource(R.drawable.logo_horizontal_full_color_border_no_legend),
                contentDescription = "",
                modifier = Modifier.height(32.dp),
            )
        },
        actions = {
            Box(
                modifier =
                Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(50.dp),
                    ).background(eldarPayLightBlueColor)
                    .clickable { onClick() },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(
                        horizontal = PADDING_LARGE, vertical = PADDING_SMALL
                    ),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_app_top_bar_logout),
                        modifier = Modifier.height(16.dp),
                        contentDescription = "App Bar Exit Icon",
                        tint = eldarPayLightBlueCardColor,
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Salir",
                        fontFamily = futuraFontFamily,
                        fontSize =FONT_SIZE_BODY_MEDIUM,
                        color = eldarPayLightBlueCardColor,
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
        },
    )
}