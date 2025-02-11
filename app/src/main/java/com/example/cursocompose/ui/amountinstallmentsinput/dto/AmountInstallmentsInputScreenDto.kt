package com.example.cursocompose.ui.amountinstallmentsinput.dto

import com.example.cursocompose.util.Constants.QUOTE_1
import com.example.cursocompose.util.Constants.QUOTE_12
import com.example.cursocompose.util.Constants.QUOTE_2
import com.example.cursocompose.util.Constants.QUOTE_24
import com.example.cursocompose.util.Constants.QUOTE_6

data class AmountInstallmentsInputScreenDto(
    val pin: String,
    val isSheetVisible: Boolean = false,
    val isExpanded: Boolean = false,
    val selectedInstallments: String,
    val installmentsListOption: List<String> = listOf(
        QUOTE_1,
        QUOTE_2,
        QUOTE_6,
        QUOTE_12,
        QUOTE_24
    ),
)