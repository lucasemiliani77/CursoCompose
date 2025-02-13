package com.example.cursocompose.ui.amountinstallmentsinput.dto

data class AmountInstallmentsInputScreenDto(
    val pin: String = "",
    val selectedInstallment: String? = null,
    val isSheetVisible: Boolean = false,
    val isExpanded: Boolean = false,
    val selectedInstallments: String,
    val installmentsListOption: List<String> = listOf(),
)
