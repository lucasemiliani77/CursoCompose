package com.example.cursocompose.ui.bluethoothdevices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursocompose.ui.bluethoothdevices.dto.BluetoothDevices
import com.example.cursocompose.ui.component.text.SmallText
import com.example.cursocompose.ui.resource.mediumRoundedCornerShape
import com.example.cursocompose.ui.theme.eldarPayDarkTextColor
import com.example.cursocompose.ui.theme.eldarPaySuccessLightGreen

@Composable
fun DeviceItem(device: BluetoothDevices) {
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
                        color = if (device.isConected) eldarPaySuccessLightGreen else Color.Transparent,
                        shape = mediumRoundedCornerShape
                    )
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
