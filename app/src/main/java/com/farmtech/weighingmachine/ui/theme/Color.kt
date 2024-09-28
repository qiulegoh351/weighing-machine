package com.farmtech.weighingmachine.ui.theme

import androidx.compose.ui.graphics.Color


// ========================== PRIMARY
private val primary = Color(0xFF00341B)
private val primary50 = Color(0xFFC6EAD0)
private val primary100 = Color(0xffdcf0e1)
private val primary200 = Color(0xfff6f9f8)
private val primaryButton = Color(0xff024e2a)

// ========================== SECONDARY

// ========================== TYPOGRAPHY
private val textPrimary = Color(0xFF1B211E)
private val textSecondary = Color(0xFF606562)

// ========================== DIVIDER
private val divider = Color(0xffd9d9d9)
private val darkGreyDivider = Color(0xff606562)
private val border = Color(0xffe7e7ef)
private val surface = Color(0xfff6f9f8)
// ========================== COLOR
private val error = Color(0xffff4d49)
private val red50 = Color(0xFFFFD6D5)

data class ThemeColors(
    val primary: Color,
    val primary50: Color,
    val primary100: Color,
    val primary200: Color,
    val primaryButton: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val surface: Color,
    val error: Color,
    val red50: Color,


    val border: Color,
    val divider: Color,
    val darkGreyDivider: Color,
)

val ColorTheme = ThemeColors(
    primary,
    primary50,
    primary100,
    primary200,
    primaryButton,
    textPrimary,
    textSecondary,
    surface,
    error,
    red50,
    border,
    divider,
    darkGreyDivider
)