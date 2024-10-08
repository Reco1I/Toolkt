package com.reco1l.toolkt.kotlin

import kotlin.math.max
import kotlin.math.min
import kotlin.reflect.KClass


// Checks

/**
 * Safe check if an element is in a nullable array. If the array is null then the result is `false`.
 */
infix operator fun <T>T.contains(collection: Collection<T>?): Boolean = collection != null && this in collection

/**
 * Finds if there's any coincidence between two lists.
 */
fun <T> List<T>.anyCoincidence(other: Collection<T>): Boolean {
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
fun <T> List<T>.anyDifference(other: Collection<T>): Boolean {
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
fun <T> List<T>.nextTo(element: T, circular: Boolean = false, fallback: T = element): T? {

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
fun <T> List<T>.previousTo(element: T, circular: Boolean = false, fallback: T = element): T? {

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
inline fun <T> List<T>.fastForEach(block: (T) -> Unit) {
    for (i in indices) {
        block(this[i])
    }
}

/**
 * Same as [fastForEach] but with indices.
 */
inline fun <T> List<T>.fastForEachIndexed(block: (index: Int, element: T) -> Unit) {
    for (i in indices) {
        block(i, this[i])
    }
}


// Modification

/**
 * Iterates all over the list removing the elements from start or from the end depending of [reversed]
 * parameter.
 */
inline fun <T> MutableList<T>.forEachTrim(reversed: Boolean = false, block: (T) -> Unit) {
    while (isNotEmpty()) {
        block(if (reversed) removeLast() else removeFirst())
    }
}


// Mappings

/**
 * Iterates all over the list transforming the result and returning it at the end.
 */
inline fun <T, R : Any?>List<T>.forEachLet(block: (element: T, result: R?) -> R?): R?
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
 * Note: This is a missing function in Kotlin standard library may be implemented later.
 */
inline fun <T> List<T>.sumOf(selector: (T) -> Float): Float {
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
inline fun <T> List<T>.fastMaxOf(selector: (T) -> Float): Float {
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
inline fun <T> List<T>.fastMinOf(selector: (T) -> Float): Float {
    var result = selector(this[0])
    for (i in indices) {
        result = min(result, selector(this[i]))
    }
    return result
}


// Constructors

/**
 * Store instances from a class inheritors as singletons.
 */
fun <T : Any> instanceMapOf() = HashMap<KClass<out T>, T>()
