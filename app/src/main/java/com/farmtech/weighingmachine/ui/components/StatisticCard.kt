package com.farmtech.weighingmachine.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.R
import com.farmtech.weighingmachine.model.Statistic
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.components.material.TouchableOpacity
import com.farmtech.weighingmachine.ui.components.utils.formatNumberWithCommas
import com.farmtech.weighingmachine.ui.components.utils.hexToColor
import com.farmtech.weighingmachine.ui.components.utils.isEmpty
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@Composable
fun StatisticCard(data: Statistic, onClick: (value: Number) -> Unit, modifier: Modifier = Modifier) {
    val statistic = data.statistic

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified
        ),
        modifier = modifier
            .height(120.dp),
        border = BorderStroke(1.dp, ColorTheme.border),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        TouchableOpacity(onClick = { onClick(data.crop_id)}) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CircleShape(hexToColor(data.color))
                        Text("${data.percentage}%")
                        HorizontalDivider(
                            color = ColorTheme.darkGreyDivider,
                            modifier = Modifier.width(12.dp)
                        )
                        Text("${formatNumberWithCommas(data.weight)} KG")
                    }
                    Text(data.crop_name)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MoreDetailButton(onClick = {})
                    StatisticBadge(
                        percentage = statistic?.value ?: 0.0,
                        status = statistic?.slug ?: ""
                    )
                }
            }
        }
    }
}

@Composable
private fun CircleShape(color: Color?) {
    Box(
        modifier = Modifier
            .size(16.dp) // Set the size of the circle
            .clip(CircleShape) // Make it circular
            .background(color ?: Color.Unspecified) // Apply the red color
    )
}

enum class BadgeStatus(
    val backgroundColor: Color?,
    val color: Color?,
    @StringRes val icon: Int
) {
    INCREASE(ColorTheme.primary100, ColorTheme.primary, R.drawable.increase_arrow),
    DECREASE(ColorTheme.red50, ColorTheme.error, R.drawable.decrease_arrow)
}

@Composable
fun StatisticBadge(percentage: Number?, status: String) {
    val config = when (status) {
        "increase" -> BadgeStatus.INCREASE
        "decrease" -> BadgeStatus.DECREASE
        else -> null
    }

    if (isEmpty(config)) return
    Box(
        modifier = Modifier
            .background(
                color = config?.backgroundColor ?: Color.Unspecified,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                "${formatNumberWithCommas(percentage)}%",
                color = config?.color ?: ColorTheme.textPrimary,
                style = TypographyTheme.titleSmall
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = config?.icon!!),
                contentDescription = "icon",
                tint = Color.Unspecified
            )
        }
    }
}