package com.example.cursocompose.ui.bluethoothdevices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cursocompose.R
import com.example.cursocompose.ui.bluethoothdevices.dto.BluetoothDevices
import com.example.cursocompose.ui.component.EldarPayTopAppBar
import com.example.cursocompose.ui.component.button.PrimaryButton
import com.example.cursocompose.ui.component.text.NormalText
import com.example.cursocompose.ui.component.text.SmallText
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_EXTRA_LARGE
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_LARGE
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_NONE
import com.example.cursocompose.ui.resource.EldarSizes.SPACER_EXTRA_SMALL
import com.example.cursocompose.ui.theme.eldarPayLightBlueSecondaryColor

@Composable
fun BlueToothDevicesScreen(viewModel: BlueToothDevicesScreenViewModel = hiltViewModel()) {
    Column(
        Modifier
            .fillMaxSize()
            .background(eldarPayLightBlueSecondaryColor)
            .padding(horizontal = PADDING_NONE, vertical = PADDING_LARGE)
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            EldarPayTopAppBar()

            SmallText(
                text = stringResource(R.string.bluetooth_devices_screen_title),
                modifier = Modifier
                    .padding(horizontal = PADDING_LARGE, vertical = PADDING_EXTRA_LARGE)
                    .fillMaxWidth(),
            )

            DeviceList(getMockDevices())
        }

        Column(
            modifier = Modifier
                .weight(1F)
        ) {
            NormalText(
                text = stringResource(R.string.bluetooth_devices_screen_sub_title),
                modifier = Modifier
                    .padding(horizontal = PADDING_LARGE, vertical = PADDING_EXTRA_LARGE)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(SPACER_EXTRA_SMALL))

            DeviceList(getMockDevices())

            Spacer(Modifier.height(SPACER_EXTRA_SMALL))
        }

        PrimaryButton(
            text = stringResource(R.string.bluetooth_devices_screen_button_title),
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

fun getMockDevices(): List<BluetoothDevices> {
    return listOf(
        BluetoothDevices(name = "Bluetooth 1", icon = R.drawable.baseline_bluetooth_24, status = "Conectar", true),
        BluetoothDevices(name = "Bluetooth 2", icon = R.drawable.baseline_bluetooth_24, status = "Conectar", false),
        BluetoothDevices(name = "Bluetooth 3", icon = R.drawable.baseline_bluetooth_24, status = "Conectar", false),
    )
}

@Composable
fun DeviceList(devices: List<BluetoothDevices>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(devices) { device ->
            DeviceItem(device)
        }
    }
}

