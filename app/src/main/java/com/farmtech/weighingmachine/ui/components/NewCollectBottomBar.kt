package com.farmtech.weighingmachine.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.R
import com.farmtech.weighingmachine.ui.components.material.Button
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@Composable
fun NewCollectBottomBar(weight: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .shadow(elevation = 1.dp)
            .padding(horizontal = 40.dp, vertical = 14.dp),
        color = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedButton(
                onClick = { /* Your click action here */ },
                border = BorderStroke(
                    1.dp,
                    ColorTheme.primary
                ), // Customize the border stroke and color
                shape = MaterialTheme.shapes.small.copy(CornerSize(8.dp)),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.new_collect_list), // Start icon
                        contentDescription = "New Collect List",
                        tint = Color.Unspecified
                    )
                    Text(
                        "Collect List",
                        color = ColorTheme.textPrimary,
                        style = TypographyTheme.titleMedium
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.durian_sample),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxHeight()
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Weight (KG)",
                        color = ColorTheme.textPrimary,
                        style = TypographyTheme.titleMedium
                    )
                    Text(
                        weight,
                        color = ColorTheme.textPrimary,
                        style = TypographyTheme.displayLarge
                    )

                }

                Button(
                    onClick = { /* Do something! */ },
                    contentPadding = PaddingValues(horizontal = 40.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {

                    Text(
                        "Save & Next",
                        color = Color.White, style = TypographyTheme.titleMedium
                    )
                }

            }
        }
    }

}


