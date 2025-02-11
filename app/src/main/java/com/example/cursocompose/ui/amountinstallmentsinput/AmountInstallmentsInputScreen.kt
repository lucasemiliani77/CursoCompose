package com.example.cursocompose.ui.amountinstallmentsinput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cursocompose.R
import com.example.cursocompose.ui.component.DropdownButton
import com.example.cursocompose.ui.component.PrimaryButton
import com.example.cursocompose.ui.component.numpad.NumPad
import com.example.cursocompose.ui.component.numpad.NumPadTextView
import com.example.cursocompose.ui.component.numpad.NumPadType
import com.example.cursocompose.ui.component.text.BigText
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_LARGE
import com.example.cursocompose.ui.resource.EldarSizes.SPACER_BIG
import com.example.cursocompose.ui.resource.EldarSizes.SPACER_MEDIUM
import com.example.cursocompose.ui.resource.EldarSizes.TEXT_VIEW_HEIGHT
import com.example.cursocompose.ui.theme.eldarPayDarkTextColor
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
            .padding(PADDING_LARGE),
            verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(SPACER_BIG))

        BigText(
            text = stringResource(R.string.amount_installments_input_screen_title),
            color = eldarPayDarkTextColor
        )

        Spacer(modifier = Modifier.height(SPACER_BIG))

        NumPadTextView(
            text = state.pin,
            hasError = false,
            errorMessage = stringResource(R.string.numpad_textview_error_message),
            type = NumPadType.PESOS,
            textViewHeight = TEXT_VIEW_HEIGHT,
            click = {
                viewModel.setSheetVisible(true)
                focusManager.clearFocus()
            }
        )

        Spacer(modifier = Modifier.height(SPACER_MEDIUM))

        var isExpanded by remember { mutableStateOf(false) }

        DropdownButton(
            isExpanded = isExpanded,
            labelText = stringResource(R.string.amount_installments_input_screen_drop_down_placeholder),
            selectedOption = state.selectedInstallment,
            listOption = state.installmentsListOption,
            onExpandedChange = { isExpanded = it },
            onValueSelect = { value ->
                viewModel.selectInstallment(value)
                isExpanded = false
            },
            onDismissRequest = { isExpanded = false }
        )

        PrimaryButton(
            text = stringResource(R.string.amount_installments_input_screen_button_title)
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
            modifier = Modifier.padding(bottom = PADDING_LARGE),
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