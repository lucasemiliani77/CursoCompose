package com.example.cursocompose.ui.amountinstallmentsinput

import androidx.lifecycle.ViewModel
import com.example.cursocompose.ui.amountinstallmentsinput.dto.AmountInstallmentsInputScreenDto
import com.example.cursocompose.util.Constants.QUOTE_1
import com.example.cursocompose.util.Constants.QUOTE_2
import com.example.cursocompose.util.Constants.QUOTE_6
import com.example.cursocompose.util.Constants.QUOTE_12
import com.example.cursocompose.util.Constants.QUOTE_24
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AmountInstallmentsInputScreenViewModel @Inject constructor() : ViewModel() {

    private val installmentsOptions = listOf(
        QUOTE_1,
        QUOTE_2,
        QUOTE_6,
        QUOTE_12,
        QUOTE_24
    )

    private val _state = MutableStateFlow(
        AmountInstallmentsInputScreenDto(
            selectedInstallments = "",
            installmentsListOption = installmentsOptions
        )
    )
    val state: StateFlow<AmountInstallmentsInputScreenDto> = _state

    fun updatePin(digit: String) {
        _state.update { it.copy(pin = it.pin + digit) }
    }

    fun deleteLastDigit() {
        _state.update { it.copy(pin = if (it.pin.isNotEmpty()) it.pin.dropLast(1) else "") }
    }

    fun selectInstallment(option: String) {
        _state.update { it.copy(selectedInstallment = option, isExpanded = false) }
    }

    fun setSheetVisible(visible: Boolean) {
        _state.update { it.copy(isSheetVisible = visible) }
    }

}
