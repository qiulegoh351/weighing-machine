package com.farmtech.weighingmachine.ui.components.utils

fun isEmpty(value: Any?): Boolean {
    return when (value) {
        null -> true
        is String -> value.isBlank()
        is Collection<*> -> value.isEmpty()
        is Map<*, *> -> value.isEmpty()
        is Array<*> -> value.isEmpty()
        else -> false
    }
}