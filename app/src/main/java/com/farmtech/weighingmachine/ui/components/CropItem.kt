package com.farmtech.weighingmachine.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.R
import com.farmtech.weighingmachine.model.Crop
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.components.material.TouchableOpacity
import com.farmtech.weighingmachine.ui.components.utils.formatNumberWithCommas
import com.farmtech.weighingmachine.ui.components.utils.logMessage
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@Composable
fun CropItem(modifier: Modifier = Modifier, data: Crop, onClick: (data: Crop, action: String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified
        ),
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, ColorTheme.border),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        TouchableOpacity(onClick = { onClick(data, "view") }) {
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(onClick = {
                        onClick(data, "delete")
                        logMessage("delete")
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.trash),
                            contentDescription = "Delete",
                            tint = Color.Unspecified
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.durian_sample),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(width = 76.dp, height = 56.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 8.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(data.cropNo)
                                HorizontalDivider(
                                    color = ColorTheme.darkGreyDivider,
                                    modifier = Modifier.width(12.dp)
                                )
                                Text(data.collectDate, style = TypographyTheme.titleSmall)
                            }
                            Text("${formatNumberWithCommas(data.weight)} KG")
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(data.cropItemName)
                            MoreDetailButton(onClick = {})
                        }
                    }
                }

            }
        }
    }
}