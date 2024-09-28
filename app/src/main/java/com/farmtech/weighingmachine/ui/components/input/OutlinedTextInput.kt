package com.farmtech.weighingmachine.ui.components.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
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
fun OutlinedTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
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

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
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
        textStyle = textStyle
    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = value,
            visualTransformation = visualTransformation,
            innerTextField = innerTextField,
            placeholder = {
                Text(
                    placeholder,
                    color = if (focusState) ColorTheme.textPrimary else Color(0xff606562)
                )
            },
            singleLine = singleLine,
            enabled = enabled,
            colors = colors.copy(
                // Modify the placeholder color when not focused
                unfocusedPlaceholderColor = Color(0xff606562),
                focusedPlaceholderColor = ColorTheme.textPrimary,
            ),
            interactionSource = interactionSource,
            contentPadding = PaddingValues(10.dp), // this is how you can remove the padding
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
        )
    }
}