package com.example.cursocompose.ui.bluethoothdevices

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cursocompose.R
import com.example.cursocompose.ui.component.EldarPayTopAppBar
import com.example.cursocompose.ui.component.button.PrimaryButton
import com.example.cursocompose.ui.component.text.NormalText
import com.example.cursocompose.ui.component.text.SmallText
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_EXTRA_LARGE
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_LARGE
import com.example.cursocompose.ui.resource.EldarSizes.PADDING_NONE
import com.example.cursocompose.ui.resource.EldarSizes.SPACER_EXTRA_SMALL
import com.example.cursocompose.ui.resource.EldarSizes.SPACER_SMALL
import com.example.cursocompose.ui.resource.mediumRoundedCornerShape
import com.example.cursocompose.ui.theme.eldarPayDarkTextColor
import com.example.cursocompose.ui.theme.eldarPayLightBlueSecondaryColor

data class Devices(
    val name: String,
    val icon: Int,
    val status: String
)

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

fun getMockDevices(): List<Devices> {
    return listOf(
        Devices(name = "Bluetooth 1", icon = R.drawable.baseline_bluetooth_24, status = "Conectar"),
        Devices(name = "Bluetooth 2", icon = R.drawable.baseline_bluetooth_24, status = "Conectar"),
        Devices(name = "Bluetooth 3", icon = R.drawable.baseline_bluetooth_24, status = "Conectar"),
    )
}

@Composable
fun DeviceList(devices: List<Devices>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(devices) { device ->
            DeviceItem(device)
        }
    }
}

@Composable
fun DeviceItem(device: Devices) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 8.dp, start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallText(
                text = device.name,
                fontSize = 18.sp,
                modifier = Modifier.weight(1F)
            )

            Row(
                modifier = Modifier
                    .width(135.dp)
                    .height(40.dp)
                    .background(
                        color = if (device.status == "Conectado") Color.Green.copy(alpha = 0.2f) else Color.Transparent,
                        shape = mediumRoundedCornerShape
                    )
                    .border(1.dp, Color.Green, mediumRoundedCornerShape)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(device.icon),
                    contentDescription = device.name,
                    tint = eldarPayDarkTextColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                SmallText(text = device.status, fontSize = 18.sp)
            }
        }

        // Espacio antes de la línea separadora
        Spacer(modifier = Modifier.height(8.dp))

        // Línea separadora
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(eldarPayDarkTextColor)
        )
    }
}
