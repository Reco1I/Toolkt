package com.reco1l.toolkt.kotlin



// Cast

/**
 * This converts the string to boolean allowing numeric booleans (`1` for `true` and `0` for `false`).
 */
fun String.toBooleanOrNull(): Boolean?
{
    if (length == 1 && (get(0) == '0' || get(0) == '1'))
        return get(0) == '1'

    return try { toBoolean() } catch (_: Exception) { null }
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