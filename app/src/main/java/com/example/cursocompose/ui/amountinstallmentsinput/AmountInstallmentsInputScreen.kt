package com.example.cursocompose.ui.amountinstallmentsinput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cursocompose.R
import com.example.cursocompose.ui.component.DropdownButton
import com.example.cursocompose.ui.component.NumPad
import com.example.cursocompose.ui.component.NumPadTextView
import com.example.cursocompose.ui.component.NumPadType
import com.example.cursocompose.ui.theme.eldarPayLightBlueSecondaryColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountInstallmentsInputScreen() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var pin by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    var isSheetVisible by remember { mutableStateOf(false) }

    LaunchedEffect(isSheetVisible) {
        if (isSheetVisible) {
            scope.launch { sheetState.show() }
        } else {
            scope.launch { sheetState.hide() }
        }
    }

    Column(
        Modifier.background(eldarPayLightBlueSecondaryColor)
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NumPadTextView(
            text = pin,
            hasError = false,
            errorMessage = stringResource(R.string.numpad_textview_error_message),
            type = NumPadType.PESOS,
            click = {
                isSheetVisible = true
                focusManager.clearFocus()
            },
            textViewHeight = 48
        )
        var isExpanded by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf<String?>(null) }

        DropdownButton(
            isExpanded = isExpanded,
            labelText = "Cuotas",
            selectedOption = selectedOption,
            listOption = listOf("1", "2", "6", "12", "24"),

            onExpandedChange = {
                isExpanded = it
            },
            onValueSelect = { value ->
                selectedOption = value
                isExpanded = false
            },
            onDismissRequest = { isExpanded = false },
        )
    }

    if (isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                isSheetVisible = false
                focusManager.clearFocus()
            },
            sheetState = sheetState,
            scrimColor = Color.Transparent,
            containerColor = Color.Transparent,
            modifier = Modifier.padding(bottom = 15.dp),
            dragHandle = { BottomSheetDefaults.DragHandle(color = Color.Transparent) }
        ) {
            NumPad(
                maxDigits = null,
                pin = pin,
                onNumberClick = { digit ->
                    pin += digit
                },
                onDeleteClick = {
                    if (pin.isNotEmpty()) {
                        pin = pin.dropLast(1)
                    }
                },
                onEnterClick = {
                    isSheetVisible = false
                    focusManager.clearFocus()
                },
                isAcceptEnabled = pin.isNotEmpty()
            )
        }
    }
}
