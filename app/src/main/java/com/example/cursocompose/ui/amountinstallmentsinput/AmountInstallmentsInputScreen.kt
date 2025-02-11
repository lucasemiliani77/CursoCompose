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
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cursocompose.R
import com.example.cursocompose.ui.component.DropdownButton
import com.example.cursocompose.ui.component.NumPad
import com.example.cursocompose.ui.component.NumPadTextView
import com.example.cursocompose.ui.component.NumPadType
import com.example.cursocompose.ui.theme.eldarPayLightBlueSecondaryColor
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AmountInstallmentsInputScreen(viewModel: AmountInstallmentsInputScreenViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    val state by viewModel.state.collectAsState()

    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(state.isSheetVisible) {
        if (state.isSheetVisible) {
            scope.launch { sheetState.show() }
        } else {
            scope.launch { sheetState.hide() }
        }
    }

    Column(
        Modifier
            .background(eldarPayLightBlueSecondaryColor)
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NumPadTextView(
            text = state.pin,
            hasError = false,
            errorMessage = stringResource(R.string.numpad_textview_error_message),
            type = NumPadType.PESOS,
            click = {
                viewModel.setSheetVisible(true)
                focusManager.clearFocus()
            },
            textViewHeight = 48
        )

        var isExpanded by remember { mutableStateOf(false) }

        DropdownButton(
            isExpanded = isExpanded,
            labelText = "Cuotas",
            selectedOption = state.selectedInstallment,
            listOption = state.installmentsListOption,
            onExpandedChange = { isExpanded = it },
            onValueSelect = { value ->
                viewModel.selectInstallment(value)
                isExpanded = false
            },
            onDismissRequest = { isExpanded = false }
        )
    }

    if (state.isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                viewModel.setSheetVisible(false)
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
                pin = state.pin,
                onNumberClick = { digit -> viewModel.updatePin(digit) },
                onDeleteClick = { viewModel.deleteLastDigit() },
                onEnterClick = {
                    viewModel.setSheetVisible(false)
                    focusManager.clearFocus()
                },
                isAcceptEnabled = state.pin.isNotEmpty()
            )
        }
    }
}