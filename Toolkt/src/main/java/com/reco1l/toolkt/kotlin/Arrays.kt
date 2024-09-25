package com.reco1l.toolkt.kotlin

import kotlin.math.max
import kotlin.math.min


// Checks

/**
 * Safe check if an element is in a nullable array. If the array is null then the result is `false`.
 */
infix operator fun <T>T.contains(array: Array<T>?): Boolean = array != null && this in array

/**
 * Finds if there's any coincidence between two lists.
 */
fun <T> Array<T>.anyCoincidence(other: Array<T>): Boolean {
    for (i in indices) {
        if (this[i] in other) {
            return true
        }
    }
    return false
}

/**
 * Finds if there's any difference between two lists.
 */
fun <T> Array<T>.anyDifference(other: Array<T>): Boolean {
    for (i in indices) {
        if (this[i] !in other) {
            return true
        }
    }
    return false
}


// Peeking

/**
 * Returns the next element in the list from the passed element.
 *
 * @param element The element to start from.
 * @param circular If `true` it'll loop back to the first element once the last is reached,
 * otherwise it'll return the element passed as [fallback].
 * @param fallback The element to return if the bound is reached and [circular] is `false`, by default
 * it's the same as [element].
 */
@JvmOverloads
fun <T> Array<T>.nextTo(element: T, circular: Boolean = false, fallback: T = element): T? {

    var index = indexOf(element)

    if (index == -1) {
        throw IllegalArgumentException("The passed element isn't present in the list.")
    }

    index++

    if (circular && index > lastIndex) {
        index = 0
    }

    return getOrNull(index) ?: fallback
}

/**
 * Returns the previous element in the list from the passed element.
 *
 * @param element The element to start from.
 * @param circular If `true` it'll loop back to the last element once the first is reached,
 * otherwise it'll return the element passed as [fallback].
 * @param fallback The element to return if the bound is reached and [circular] is `false`, by default
 * it's the same as [element].
 */
@JvmOverloads
fun <T> Array<T>.previousTo(element: T, circular: Boolean = false, fallback: T = element): T? {

    var index = indexOf(element)

    if (index == -1) {
        throw IllegalArgumentException("The passed element isn't present in the list.")
    }

    index--

    if (circular && index < 0) {
        index = size - 1
    }

    return getOrNull(index) ?: fallback
}


// Loops

/**
 * Iterates all over the list.
 *
 * Despite [forEach] this doesn't use a [Iterator] internally which can lead to a performance penalty, if
 * the list is modified while iterating it can throw an [ArrayIndexOutOfBoundsException] rather than a
 * [ConcurrentModificationException].
 */
inline fun <T> Array<T>.fastForEach(block: (T) -> Unit) {
    for (i in indices) {
        block(this[i])
    }
}

/**
 * Same as [fastForEach] but with indices.
 */
inline fun <T> Array<T>.fastForEachIndexed(block: (index: Int, element: T) -> Unit) {
    for (i in indices) {
        block(i, this[i])
    }
}


// Mappings

/**
 * Iterates all over the list transforming the result and returning it at the end.
 */
inline fun <T, R : Any?>Array<T>.forEachLet(block: (element: T, result: R?) -> R?): R?
{
    var result = block(this[0], null)
    for (i in indices) {
        result = block(this[i], result)
    }
    return result
}

/**
 * Returns the sum of all values produced by [selector] function applied to each element in the array.
 *
 * Note: This is a missing function in Kotlin standard library that can be implemented later.
 */
inline fun <T> Array<T>.sumOf(selector: (T) -> Float): Float {
    var sum = 0f
    for (i in indices) {
        sum += selector(this[i])
    }
    return sum
}


/**
 * Returns the maximum value produced by [selector] function applied to each element in the array.
 *
 * Despite [maxOf] this doesn't use a [Iterator] internally which can lead to a performance penalty, if
 * the list is modified while iterating it can throw an [ArrayIndexOutOfBoundsException] rather than a
 * [ConcurrentModificationException].
 */
inline fun <T> Array<T>.fastMaxOf(selector: (T) -> Float): Float {
    var result = selector(this[0])
    for (i in indices) {
        result = max(result, selector(this[i]))
    }
    return result
}

/**
 * Returns the minimum value produced by [selector] function applied to each element in the array.
 *
 * Despite [minOf] this doesn't use a [Iterator] internally which can lead to a performance penalty, if
 * the list is modified while iterating it can throw an [ArrayIndexOutOfBoundsException] rather than a
 * [ConcurrentModificationException].
 */
inline fun <T> Array<T>.fastMinOf(selector: (T) -> Float): Float {
    var result = selector(this[0])
    for (i in indices) {
        result = min(result, selector(this[i]))
    }
    return result
}