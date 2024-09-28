package com.farmtech.weighingmachine.ui.container.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.ui.components.PreviewField
import com.farmtech.weighingmachine.ui.components.material.Button
import com.farmtech.weighingmachine.ui.components.material.FormDialog
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.components.input.PasswordInput
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

data class FormPasswordEdit(
    val oldPassword: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",
)

@Composable
fun SecurityInfo(modifier: Modifier = Modifier) {
    val passwordEditDialog = remember { mutableStateOf(false) }
    val formPasswordEdit = remember {
        mutableStateOf<FormPasswordEdit>(
            FormPasswordEdit(
                oldPassword = "",
                newPassword = "",
                confirmPassword = ""
            )
        )
    }
    val onPasswordEditHandler: (String, String) -> Unit = { field, value ->
        formPasswordEdit.value = when (field) {
            "oldPassword" -> formPasswordEdit.value.copy(oldPassword = value)
            "newPassword" -> formPasswordEdit.value.copy(newPassword = value)
            "confirmPassword" -> formPasswordEdit.value.copy(confirmPassword = value)
            else -> formPasswordEdit.value
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(ColorTheme.primary200)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Security", style = TypographyTheme.displayMedium)

            Button(
                onClick = { passwordEditDialog.value = true },
            ) {
                Text(
                    "Change Password",
                    color = Color.White,
                    style = TypographyTheme.titleSmall
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            item {
                PreviewField("Password", "******")
            }
        }
    }

    // =============== Password Edit Dialog
    FormDialog(
        visible = passwordEditDialog,
        onDismissRequest = { passwordEditDialog.value = false },
        dialogTitle = "Change Password",
        onSubmit = { }
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            PasswordInput(
                value = formPasswordEdit.value.oldPassword,
                onValueChange = { onPasswordEditHandler("oldPassword", it) },
                label = "Old Password",
                placeholder = "Old Password"
            )

            PasswordInput(
                value = formPasswordEdit.value.newPassword,
                onValueChange = { onPasswordEditHandler("newPassword", it) },
                label = "New Password",
                placeholder = "New Password",
                enabled = false
            )

            PasswordInput(
                value = formPasswordEdit.value.confirmPassword,
                onValueChange = { onPasswordEditHandler("confirmPassword", it) },
                label = "Confirm New Password",
                placeholder = "New Password"
            )
        }
    }
}