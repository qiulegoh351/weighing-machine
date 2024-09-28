package com.farmtech.weighingmachine.ui.components.screen.cropSetting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.ui.components.material.Button
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.theme.ColorTheme

@Composable
fun CropSettingBottomBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(88.dp)
            .shadow(elevation = 1.dp)
            .padding(horizontal = 40.dp, vertical = 14.dp),
        color = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text("Last edited on 12/06/2023")

            Spacer(modifier = Modifier.width(24.dp))

            Button(
                modifier = Modifier.fillMaxHeight(),
                contentPadding = PaddingValues(vertical = 16.dp, horizontal = 40.dp),
                onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    containerColor = ColorTheme.primary
                )
            ) {
                Text("Edit Setting", color = Color.White)
            }
        }
    }
}