package com.farmtech.weighingmachine.ui.components.utils

import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.platform.SoftwareKeyboardController

fun keyboardEventHandler(
    keyEvent: KeyEvent,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
): Boolean {
    return if (keyEvent.key == Key.Enter) {
        keyboardController?.hide()
        focusManager.clearFocus(force = true)
        true
    } else false
}

fun dismissKeyboard(
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
) {
    keyboardController?.hide()
    focusManager.clearFocus(force = true)
}