package com.farmtech.weighingmachine.ui.components.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farmtech.weighingmachine.ui.components.MoreDetailButton
import com.farmtech.weighingmachine.ui.components.charts.PieChart
import com.farmtech.weighingmachine.ui.components.material.Button
import com.farmtech.weighingmachine.ui.components.material.ButtonColorVariant
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@Composable
fun StatisticSideBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Weight Record", style = TypographyTheme.displayMedium)

        PieChart(
            modifier = Modifier
                .padding(24.dp)
                .size(160.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 0.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Total Weight (KG)", style = TypographyTheme.titleMedium)
                Text(
                    "1,349,101.93", style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(0.7f),
                color = ColorTheme.divider
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Total 13 Product(s)",
                    style = TypographyTheme.titleMedium
                )
                MoreDetailButton(onClick = {})
            }
        }

        Button(onClick = {}, colorVariant = ButtonColorVariant.SECONDARY) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "More Detail"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("More Details", style = TypographyTheme.titleMedium)
        }
    }
}