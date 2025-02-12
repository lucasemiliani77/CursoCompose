package com.example.cursocompose.ui.component.numpad

import android.media.ToneGenerator
import android.media.AudioManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursocompose.R
import com.example.cursocompose.ui.theme.eldarPayDisabledGrey
import com.example.cursocompose.ui.theme.eldarPayGreenOk
import com.example.cursocompose.ui.theme.eldarPayLightBlueCardColor
import com.example.cursocompose.ui.theme.eldarPayLightBlueColor
import com.example.cursocompose.ui.theme.eldarPayPressedNumPad

val toneGenerator = ToneGenerator(AudioManager.STREAM_SYSTEM, 100)

fun playTone(toneType: Int) {
    toneGenerator.startTone(toneType, 150)
}

@Composable
fun NumPad(
    maxDigits: Int?,
    pin: String,
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onEnterClick: (String) -> Unit,
    isAcceptEnabled: Boolean,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val buttons = listOf(
            stringResource(R.string.KEY_1),
            stringResource(R.string.KEY_2),
            stringResource(R.string.KEY_3),
            stringResource(R.string.KEY_4),
            stringResource(R.string.KEY_5),
            stringResource(R.string.KEY_6),
            stringResource(R.string.KEY_7),
            stringResource(R.string.KEY_8),
            stringResource(R.string.KEY_9),
            stringResource(R.string.KEY_DELETE),
            stringResource(R.string.KEY_0),
            stringResource(R.string.KEY_ACCEPT),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            buttons.forEach { label ->
                item {
                    PinPadButton(
                        label = label,
                        isEnabled = label != "accept" || isAcceptEnabled,
                    ) {
                        when (label) {
                            "delete" -> {
                                if (pin.isNotEmpty()) {
                                    onDeleteClick()
                                    playTone(ToneGenerator.TONE_CDMA_CALLDROP_LITE)
                                }
                            }
                            "accept" -> {
                                if (isAcceptEnabled) {
                                    onEnterClick(pin)
                                    playTone(ToneGenerator.TONE_PROP_ACK)
                                }
                            }
                            else -> {
                                if (maxDigits == null || pin.length < maxDigits) {
                                    onNumberClick(label)
                                    playTone(ToneGenerator.TONE_DTMF_1)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun PinPadButton(
    label: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    var isPressed by remember { mutableStateOf(false) }

    val backgroundColor =
        when {
            !isEnabled -> eldarPayLightBlueCardColor
            isPressed -> eldarPayPressedNumPad
            else -> eldarPayLightBlueCardColor
        }
    val textColor =
        when {
            !isEnabled -> eldarPayDisabledGrey
            isPressed -> eldarPayLightBlueColor
            else -> eldarPayLightBlueColor
        }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier =
        Modifier
            .width(106.dp)
            .height(74.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = eldarPayLightBlueColor, shape = RoundedCornerShape(8.dp))
            .pointerInput(isEnabled) {
                detectTapGestures(
                    onPress = {
                        if (isEnabled) {
                            isPressed = true
                            try {
                                awaitRelease()
                                onClick()
                            } finally {
                                isPressed = false
                            }
                        }
                    },
                )
            }.padding(8.dp),
    ) {
        when (label) {
            "delete" ->
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.outline_backspace_24),
                    contentDescription = "Delete",
                    tint = if (isEnabled) eldarPayLightBlueColor else eldarPayDisabledGrey,
                    modifier = Modifier.width(50.dp).height(50.dp),
                )

            "accept" ->
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_check_new_svg),
                    contentDescription = "Confirm",
                    tint = if (isEnabled) eldarPayGreenOk else eldarPayDisabledGrey,
                    modifier = Modifier.width(70.dp).height(70.dp),
                )

            else ->
                Text(
                    text = label,
                    fontSize = 50.sp,
                    color = textColor
                )
        }
    }
}
