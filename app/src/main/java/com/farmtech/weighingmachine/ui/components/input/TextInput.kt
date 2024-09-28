package com.farmtech.weighingmachine.ui.components.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onInterceptKeyBeforeSoftKeyboard
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.farmtech.weighingmachine.ui.components.material.Text
import com.farmtech.weighingmachine.ui.components.utils.dismissKeyboard
import com.farmtech.weighingmachine.ui.components.utils.keyboardEventHandler
import com.farmtech.weighingmachine.ui.theme.ColorTheme
import com.farmtech.weighingmachine.ui.theme.TypographyTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TextInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedBorderColor = Color.Transparent,
        focusedBorderColor = Color.Transparent,
    ),
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: String = "",
    textStyle: TextStyle = TypographyTheme.titleMedium
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var focusState by remember { mutableStateOf(false) }
    val labelColor = if (enabled) ColorTheme.textPrimary else Color(0xffcbcfcd)
    val opacity = if (enabled) 1f else .7f
    val currentTextStyle = textStyle.copy(color = if (enabled) textStyle.color else Color(0xffb8bcba))

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(label, color = labelColor)

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .alpha(opacity)
                .drawBehind {
                    val borderWidth = 2.5.dp.toPx()
                    val width = size.width
                    val height = size.height
                    drawLine(
                        color = if (focusState) ColorTheme.primaryButton else Color(0xffd6dad8),
                        start = Offset(0f, height),
                        end = Offset(width, height),
                        strokeWidth = borderWidth
                    )
                }
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .onInterceptKeyBeforeSoftKeyboard { keyEvent ->
                    keyboardEventHandler(
                        keyEvent,
                        keyboardController,
                        focusManager
                    )
                }
                .onFocusChanged { focus -> focusState = focus.isFocused },
            keyboardActions = KeyboardActions(
                onDone = {
                    dismissKeyboard(keyboardController, focusManager)
                }
            ),
            readOnly = readOnly,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            enabled = enabled,
            singleLine = singleLine,
            textStyle = currentTextStyle
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        placeholder,
                        isDefaultTheme = true
                    )
                },
                singleLine = singleLine,
                enabled = enabled,
                colors = colors.copy(
                    unfocusedPlaceholderColor = Color(0xffb8bcba),
                    unfocusedContainerColor = Color(0xffeef1ef),
                    focusedContainerColor = Color(0xffeef1ef),
                    focusedPlaceholderColor = Color(0xa6b8bcba),
                    disabledPlaceholderColor = Color(0xffb8bcba),
                    disabledContainerColor = Color(0xffeef1ef),
                ),
                interactionSource = interactionSource,
                contentPadding = PaddingValues(10.dp),
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon,
            )
        }
    }
}