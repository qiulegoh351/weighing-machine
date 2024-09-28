package com.farmtech.weighingmachine.ui.components.material

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.farmtech.weighingmachine.R
import com.farmtech.weighingmachine.ui.theme.TypographyTheme
import androidx.compose.ui.window.Dialog as MaterialDialog

@Composable
fun FormDialog(
    visible: MutableState<Boolean>,
    onDismissRequest: () -> Unit,
    onSubmit: () -> Unit,
    submitLabel: String = "Save Changes",
    dialogTitle: String,
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
    ),
    shape: Shape = RoundedCornerShape(8.dp),
    content: @Composable () -> Unit
) {
    if (visible.value) {
        MaterialDialog(
            onDismissRequest, properties
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .imePadding(),
                shape = shape,
                color = Color.White,
            ) {
                KeyboardAvoidingView(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(32.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(dialogTitle, style = TypographyTheme.displayMedium)
                        TouchableOpacity(onClick = { onDismissRequest() }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.dialog_close),
                                contentDescription = "Close Dialog",
                                tint = Color.Unspecified
                            )
                        }
                    }

                    content()

                    Button(modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        onClick = onSubmit) {
                        Text(submitLabel, color = Color.White)
                    }
                }
            }
        }
    }
}