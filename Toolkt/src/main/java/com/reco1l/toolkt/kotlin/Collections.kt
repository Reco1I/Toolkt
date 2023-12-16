package com.reco1l.toolkt.kotlin

import com.reco1l.toolkt.kotlin.BoundConflict.*
import kotlin.reflect.KClass


/**
 * Add an element if the passed element isn't null.
 */
fun <T : Any> MutableCollection<T>.addIfNotNull(element: T?) = element?.let { add(it) } ?: false


/**
 * Defines the behavior when reaching the bound.
 */
enum class BoundConflict
{
    /**
     * Once the bound is reached it'll return `null`.
     */
    NULL,
    /**
     * Once the bound is reached it'll return the same object.
     */
    CLAMP,
    /**
     * Once the bound is reached it'll return the first or last element.
     */
    START_END
}


/**
 * Returns the next element in the list from the passed element.
 * @param boundConflict The behavior when reaching the bound, see [BoundConflict] for more info.
 */
fun <T>List<T>.nextOf(
    element: T,
    boundConflict: BoundConflict = NULL
): T?
{
    var index = indexOf(element)

    if (index == -1)
        throw IllegalArgumentException("The passed element isn't present in the list.")

    index++

    if (index > lastIndex)
    {
        return when(boundConflict)
        {
            NULL -> null
            CLAMP -> element
            START_END -> get(0)
        }
    }
    return get(index)
}

/**
 * Returns the previous element in the list from the passed element.
 * @param boundConflict The behavior when reaching the bound, see [BoundConflict] for more info.
 */
fun <T>List<T>.previousOf(
    element: T,
    boundConflict: BoundConflict = NULL
): T?
{
    var index = indexOf(element)

    if (index == -1)
        throw IllegalArgumentException("The passed element isn't present in the list.")

    index--

    if (index < 0)
    {
        return when(boundConflict)
        {
            NULL -> null
            CLAMP -> element
            START_END -> get(lastIndex)
        }
    }
    return get(index)
}


/**
 * Iterates all over the list removing the elements from start or from the end depending of [reversed]
 * parameter.
 */
inline fun <T>MutableList<T>.forEachTrim(reversed: Boolean = false, block: (T) -> Unit)
{
    while (isNotEmpty())
        block(if (reversed) removeLast() else removeFirst())
}

/**
 * Iterates all over the list transforming the result and returning it at the end.
 */
inline fun <T, R : Any?>Array<T>.forEachLet(block: (T) -> R): R?
{
    var result: R? = null
    forEach { result = block(it) }
    return result
}

/**
 * Covers the same functions as [associateWith] with indices.
 */
inline fun <K, V> Iterable<K>.associateWithIndexed(valueSelector: (K, Int) -> V): Map<K, V>
{
    var index = 0
    return associateWith {
        val value = valueSelector(it, index)
        index++
        value
    }
}

/**
 * Store instances from a class inheritors as singletons.
 */
fun <T : Any>instanceMapOf() = HashMap<KClass<out T>, T>()

/**
 * Safe check if an element is in a nullable array. If the array is null then the result is `false`.
 */
infix fun <T>T.safeIn(array: Array<T>?): Boolean = array != null && this in array
