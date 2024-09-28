package com.farmtech.weighingmachine.ui.components.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.components.utils.logMessage
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    onDateSelected: (Long?) -> Unit,
) {
    val datePickerState = rememberDatePickerState()
    var showModal by remember { mutableStateOf(false) }
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: convertMillisToDate(System.currentTimeMillis())

    fun onDismiss() {
        showModal = false
    }

    fun onOpenDatePicker() {
        showModal = !showModal
    }

    logMessage(selectedDate)

    Box(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        OutlinedTextInput(
            value = selectedDate,
            onValueChange = { },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date"
                )
            },
            readOnly = true,
            enabled = false,
            modifier = Modifier.clickable {
                onOpenDatePicker()
            },
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = Color(0xffd6dad8),
                disabledTextColor = ColorTheme.textPrimary,
                disabledLeadingIconColor =  ColorTheme.textPrimary,
                disabledTrailingIconColor = ColorTheme.textPrimary,
                disabledPlaceholderColor = Color(0xffd6dad8)
            ),
            placeholder = "Select Date"
        )
        if (showModal) {
            DatePickerDialog(
                onDismissRequest = { onDismiss() },
                confirmButton = {
                    TextButton(onClick = {
                        onDateSelected(datePickerState.selectedDateMillis)
                        showModal = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { onDismiss() }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}