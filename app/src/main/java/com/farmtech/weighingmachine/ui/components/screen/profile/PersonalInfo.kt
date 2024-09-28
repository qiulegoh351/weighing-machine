package com.farmtech.weighingmachine.ui.components.screen.profile

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
import com.farmtech.weighingmachine.ui.components.input.TextInput
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

data class FormProfileEdit(
    val fullName: String = "",
    val contactNumber: String = "",
    val companyId: String = "",
    val email: String = ""
)

val defaultValues = FormProfileEdit(
    fullName = "John Legend",
    contactNumber = "012-3456 789",
    companyId = "1234",
    email = "xxx@gmail.com"
)

@Composable
fun PersonalInfo(modifier: Modifier = Modifier) {
    val editDialog = remember { mutableStateOf(false) }
    val formProfileEdit = remember { mutableStateOf<FormProfileEdit>(defaultValues) }
    val onProfileEditHandler: (String, String) -> Unit = { field, value ->
        formProfileEdit.value = when (field) {
            "fullName" -> formProfileEdit.value.copy(fullName = value)
            "contactNumber" -> formProfileEdit.value.copy(contactNumber = value)
            "companyId" -> formProfileEdit.value.copy(companyId = value)
            "email" -> formProfileEdit.value.copy(email = value)
            else -> formProfileEdit.value
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
            Text("Personal Info", style = TypographyTheme.displayMedium)

            Button(
                onClick = { editDialog.value = true }
            ) {
                Text(
                    "Edit Profile",
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
                PreviewField("Company ID", "123123")
            }
            item {
                PreviewField("Contact Number", "+60 12-3456789")
            }
            item {
                PreviewField("Full Name", "John Legend")
            }
            item {
                PreviewField("Email Address", "johnlegend@gmail.com")
            }
        }
    }

    // =============== Edit Profile Dialog
    FormDialog(
        onDismissRequest = { editDialog.value = false },
        visible = editDialog,
        dialogTitle = "Edit Profile",
        onSubmit = {}
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            TextInput(
                value = formProfileEdit.value.fullName,
                onValueChange = { onProfileEditHandler("fullName", it) },
                label = "Full Name",
                placeholder = "Full Name"
            )

            TextInput(
                value = formProfileEdit.value.contactNumber,
                onValueChange = { onProfileEditHandler("contactNumber", it) },
                label = "Contact Number",
                placeholder = "Contact Number",
                enabled = false
            )

            TextInput(
                value = formProfileEdit.value.companyId,
                onValueChange = { onProfileEditHandler("companyId", it) },
                label = "Company ID",
                placeholder = "Company ID"
            )

            TextInput(
                value = formProfileEdit.value.email,
                onValueChange = { onProfileEditHandler("email", it) },
                label = "Email Address",
                placeholder = "Email Address"
            )
        }
    }
}