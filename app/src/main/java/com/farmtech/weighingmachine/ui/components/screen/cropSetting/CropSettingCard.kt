package com.farmtech.weighingmachine.ui.components.screen.cropSetting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.R
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.components.material.TouchableOpacity
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@Composable
fun CropSettingCard(
    modifier: Modifier = Modifier,
    label: String = "",
    category: String = "",
    isRequired: Boolean = false,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = ColorTheme.surface,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // ================= Label
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .wrapContentSize(align = Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = label,
                        )
                        if (isRequired) {
                            Text(
                                "*",
                                color = ColorTheme.error,
                            )
                        }
                    }

                    VerticalDivider(
                        modifier = Modifier
                            .height(32.dp)
                            .width(2.dp),
                        color = ColorTheme.divider,
                        thickness = 2.dp
                    )

                    Text(
                        category,
                        style = TypographyTheme.titleMedium.copy(
                            fontWeight = FontWeight.Normal
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                // ================= Action
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    VerticalDivider(
                        modifier = Modifier
                            .height(32.dp)
                            .width(2.dp),
                        color = ColorTheme.divider,
                        thickness = 2.dp
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(28.dp)
                    ) {
                        TouchableOpacity(onClick = onEdit) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.edit),
                                    contentDescription = "Edit",
                                    modifier = Modifier.size(24.dp),
                                    tint = ColorTheme.textSecondary
                                )
                                Text("Edit", color = ColorTheme.textSecondary)
                            }
                        }

                        TouchableOpacity(onClick = onDelete) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.trash),
                                    contentDescription = "Delete",
                                    modifier = Modifier.size(24.dp),
                                    tint = ColorTheme.textSecondary
                                )
                                Text("Delete", color = ColorTheme.textSecondary)
                            }
                        }
                    }
                }
            }
        }
    }
}