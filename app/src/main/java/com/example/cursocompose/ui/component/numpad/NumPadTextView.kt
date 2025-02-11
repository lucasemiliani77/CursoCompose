package com.example.cursocompose.ui.component.numpad

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.cursocompose.R
import com.example.cursocompose.ui.resource.EldarFontSize.FONT_SIZE_BODY_LARGE
import com.example.cursocompose.ui.resource.EldarFontSize.FONT_SIZE_MAX_NUM_PAD_TEXT_VIEW
import com.example.cursocompose.ui.resource.EldarFontSize.FONT_SIZE_MIN_NUM_PAD_TEXT_VIEW
import com.example.cursocompose.ui.resource.EldarFontSize.FONT_SIZE_NUM_PAD_TEXT_VIEW_DNI_PIN
import com.example.cursocompose.ui.resource.EldarSizes.BORDER_WIDTH
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_LARGE
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_SMALL
import com.example.cursocompose.ui.resource.mediumRoundedCornerShape
import com.example.cursocompose.ui.theme.eldarPayBlue
import com.example.cursocompose.ui.theme.eldarPayErrorRed
import com.example.cursocompose.ui.theme.eldarPayLightBlue
import com.example.cursocompose.ui.theme.eldarPayNumPadWhite
import com.example.cursocompose.ui.theme.eldarPayTextBlue
import com.example.cursocompose.ui.theme.eldarPayTextViewBackgroundFocus
import com.example.cursocompose.util.Constants.CURRENCY_SYMBOL_AR
import com.example.cursocompose.util.Constants.CURRENCY_SYMBOL_DOLLAR
import com.example.cursocompose.util.Constants.NUM_PAD_TEXT_VIEW_MAX_LENGTH
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

enum class NumPadType { DNI, PIN, PESOS, DOLLARS }

@Composable
fun NumPadTextView(
    text: String,
    hasError: Boolean,
    errorMessage: String = "",
    type: NumPadType,
    textViewHeight: Int = 48,
    click: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val backgroundColor = when {
        isPressed -> eldarPayTextViewBackgroundFocus
        isFocused -> eldarPayTextViewBackgroundFocus
        else -> eldarPayNumPadWhite
    }

    val textColor = when {
        isPressed -> eldarPayBlue
        isFocused -> eldarPayTextBlue
        else -> eldarPayBlue
    }
    val maskedText =
        when (type) {
            NumPadType.DNI -> formatDNI(text)
            NumPadType.PIN -> text.map { stringResource(R.string.pin_mask) }.joinToString("")
            NumPadType.PESOS -> formatCurrency(text, CURRENCY_SYMBOL_AR)
            NumPadType.DOLLARS -> formatCurrency(text, CURRENCY_SYMBOL_DOLLAR)
        }

    val fontSize =
        when (type) {
            NumPadType.PESOS, NumPadType.DOLLARS -> calculateFontSize(maskedText)
            else -> FONT_SIZE_NUM_PAD_TEXT_VIEW_DNI_PIN
        }

    Column(
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = click
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .height(textViewHeight.dp)
                .clip(mediumRoundedCornerShape)
                .background(backgroundColor)
                .border(
                    width = BORDER_WIDTH,
                    color = if (hasError) eldarPayErrorRed else eldarPayLightBlue,
                    shape = mediumRoundedCornerShape,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = maskedText,
                color = if (hasError) eldarPayErrorRed else textColor,
                fontSize = fontSize,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        if (hasError) {
            Text(
                text = errorMessage,
                color = eldarPayErrorRed,
                fontSize = FONT_SIZE_BODY_LARGE,
                textAlign = TextAlign.Start,
                modifier =
                Modifier
                    .padding(top = PADDING_SMALL, start = PADDING_LARGE, end = PADDING_LARGE)
                    .fillMaxWidth(),
            )
        }
    }
}

fun formatDNI(dni: String): String {
    val paddedDni = dni.padStart(8, ' ')
    return "${paddedDni.substring(0, 2)} ${paddedDni.substring(2, 5)} ${paddedDni.substring(5, 8)}"
}

fun formatCurrency(
    amount: String,
    currencySymbol: String,
): String {
    val numericInput = amount.replace("[^\\d]".toRegex(), "")

    if (numericInput.isEmpty()) {
        return "$currencySymbol 0,00"
    }

    val numericValue =
        numericInput.toBigDecimal().setScale(2, java.math.RoundingMode.HALF_UP).movePointLeft(2)

    val symbols =
        DecimalFormatSymbols(Locale("es", "AR")).apply {
            groupingSeparator = '.'
            decimalSeparator = ','
        }

    val formatter = DecimalFormat("###,##0.00", symbols)

    return "$currencySymbol ${formatter.format(numericValue)}"
}

@Composable
fun calculateFontSize(text: String): TextUnit {
    val maxLength = NUM_PAD_TEXT_VIEW_MAX_LENGTH
    val maxFontSizePx = FONT_SIZE_MAX_NUM_PAD_TEXT_VIEW
    val minFontSizePx = FONT_SIZE_MIN_NUM_PAD_TEXT_VIEW

    val textLength = text.length
    val fontSizePx =
        when {
            textLength <= maxLength -> maxFontSizePx
            else ->
                (maxFontSizePx * (maxLength.toFloat() / textLength))
                    .toInt()
                    .coerceIn(minFontSizePx, maxFontSizePx)
        }

    val density = LocalDensity.current
    return with(density) { fontSizePx.toSp() }
}
