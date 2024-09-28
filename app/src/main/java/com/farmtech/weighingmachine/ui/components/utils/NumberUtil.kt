package com.farmtech.weighingmachine.ui.components.utils

import java.text.DecimalFormat

data class NumberWithCommaOption(
    val alwaysDecimal: Boolean = false,
    val fractionDigits: Int = 2
)

fun formatNumberWithCommas(
    value: Any?,
    options: NumberWithCommaOption? = NumberWithCommaOption()
): String {
    // Convert input to a Number
    val number = when (value) {
        is Number -> value
        is String -> {
            value.toDoubleOrNull() ?: 0.0
        }
        else -> 0.0
    }

    val (alwaysDecimal, fractionDigits) = options!!
    val fractionPart = if (alwaysDecimal)  "0".repeat(fractionDigits) else "#".repeat(fractionDigits)
    val pattern = "#,###.${fractionPart}"
    val decimalFormat = DecimalFormat(pattern)

    return decimalFormat.format(number ?: 0)
}
