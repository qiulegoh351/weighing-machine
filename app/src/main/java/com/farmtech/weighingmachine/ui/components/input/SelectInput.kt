package com.farmtech.weighingmachine.ui.components.input

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.R
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T, Value> SelectInput(
    modifier: Modifier = Modifier,
    options: List<T>,
    label: String,
    placeholder: String = "",
    value: Value?,
    onValueChange: (Value?) -> Unit,
    loading: Boolean = false,
    disabled: Boolean = false,
    deSelect: Boolean = true,
    labelKey: (T) -> String,
    valueKey: (T) -> Value,
) {
    var expanded by remember { mutableStateOf(false) }
    val enableExpand = !disabled && !loading

    @Composable
    fun renderTrailingIcon() {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = ColorTheme.primaryButton,
                strokeWidth = 2.5.dp,
            )
            return
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.select_dropdown),
            contentDescription = "Select Dropdown",
            tint = Color.Unspecified
        )
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (enableExpand) {
                expanded = !expanded
            }
        },
        modifier = modifier
    ) {
        val selectedOption = options.find { valueKey(it) == value }
        TextInput(
            readOnly = true,
            value = selectedOption?.let { labelKey(it) } ?: "",
            placeholder = placeholder,
            onValueChange = {},
            label = label,
            enabled = enableExpand,
            trailingIcon = { renderTrailingIcon() },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.White,

        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = labelKey(option), style = TypographyTheme.titleSmall) },
                    onClick = {
                        val isSameValue = deSelect && valueKey(option) == value
                        expanded = false
                        if (isSameValue) {
                            onValueChange(null) // Clear selection
                        } else {
                            onValueChange(valueKey(option)) // Select new option
                        }
                    },
                    contentPadding = PaddingValues(10.dp)
                )
            }
        }
    }
}