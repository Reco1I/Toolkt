package com.reco1l.toolkt.kotlin


// Cast

/**
 * This converts the string to boolean allowing numeric booleans.
 *
 * Allowed values:
 * * `0` -> `false`
 * * `1` -> `true`
 * * `0.0` -> `false`
 * * `1.0` -> `true`
 * * `true` -> `true`
 * * `false` -> `false`
 * * `TRUE` -> `true`
 * * `FALSE` -> `false`
 */
fun String?.toBooleanOrNull(): Boolean? {

    if (isNullOrEmpty()) {
        return null
    }

    // Avoiding to run equals() comparison again.
    val isTrue = equals("true", true)
    if (isTrue || equals("false", true)) {
        return isTrue
    }

    val long = toLongOrNull()
    if (long == 1L || long == 0L) {
        return long == 1L
    }

    val double = toDoubleOrNull()
    if (double == 1.0 || double == 0.0) {
        return double == 1.0
    }

    return null
}


// Scope functions

/**
 * Special block to ignore exceptions
 */
inline fun <R : Any> runSafe(block: () -> R?): R? {
    return try {
        block()
    } catch (_: Exception) {
        null
    }
}