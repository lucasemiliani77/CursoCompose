package com.example.cursocompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cursocompose.R
import com.example.cursocompose.ui.component.text.NormalText
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_LARGE
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_NONE
import com.example.cursocompose.ui.resource.mediumRoundedCornerShape
import com.example.cursocompose.ui.theme.eldarPayBlue
import com.example.cursocompose.ui.theme.eldarPayDarkTextColor
import com.example.cursocompose.ui.theme.eldarPayNumPadWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownButton(
    isExpanded: Boolean = false,
    labelText: String = "Cuotas",
    selectedOption: String? = "1",
    listOption: List<String> = listOf("01", "02", "06", "12", "24"),
    onExpandedChange: (Boolean) -> Unit = { },
    onValueSelect: (String) -> Unit = { },
    onDismissRequest: () -> Unit = { },
    horizontalPadding: Dp
) {
    ExposedDropdownMenuBox(
        modifier = Modifier.padding(horizontal = horizontalPadding),
        expanded = isExpanded,
        onExpandedChange = onExpandedChange,
    ) {
        DropdownButtonLabel(
            labelText = labelText,
            selectedOption = selectedOption,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier =
                    Modifier
                        .rotate(if (isExpanded) 180f else 0f)
                        .width(40.dp)
                        .height(40.dp)
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                    tint = eldarPayDarkTextColor,
                )
            },
        )

        Box(
            modifier = Modifier
                .border(1.dp, eldarPayBlue, mediumRoundedCornerShape)
                .background(eldarPayNumPadWhite)
                .clip(mediumRoundedCornerShape)
        ) {
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = onDismissRequest,
                modifier = Modifier
                    .background(eldarPayNumPadWhite)
                    .padding(horizontal = horizontalPadding)
            ) {
                listOption.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            NormalText(
                                text = option,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        },
                        onClick = { onValueSelect(option) },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun DropdownButtonLabel(
    modifier: Modifier = Modifier,
    labelText: String = "Cuotas",
    selectedOption: String? = null,
    trailingIcon: @Composable () -> Unit = {
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_back),
            contentDescription = "",
            tint = eldarPayDarkTextColor,
        )
    },
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(1.dp, eldarPayBlue, mediumRoundedCornerShape)
            .padding(horizontal = PADDING_NONE, vertical = PADDING_NONE)
            .clip(mediumRoundedCornerShape)
            .background(eldarPayNumPadWhite)
    ) {
        NormalText(
            text = selectedOption ?: labelText,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = PADDING_LARGE, vertical = PADDING_NONE),
        )

        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            trailingIcon()
        }
    }
}
