package com.reco1l.toolkt.kotlin


/**
 * Special block to ignore exceptions
 */
inline fun <R : Any> ignoreException(block: () -> R?): R?
{
    return try { block() } catch (_: Exception) { null }
}

/**
 * Prettier try-catch with result returning.
 */
inline fun <T> (() -> T).orCatch(onException: (e: Exception) -> T): T
{
    return try { this() } catch (e: Exception) { onException(e) }
}