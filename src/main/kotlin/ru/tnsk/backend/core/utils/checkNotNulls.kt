package ru.tnsk.backend.core.utils

fun checkNotNulls(vararg values: Any?): Boolean {
    return values.all { it != null }
}