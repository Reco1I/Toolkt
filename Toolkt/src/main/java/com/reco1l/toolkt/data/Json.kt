package com.reco1l.toolkt.data

import org.jetbrains.annotations.ApiStatus.Experimental
import org.json.JSONArray
import org.json.JSONObject
import kotlin.reflect.full.primaryConstructor


/**
 *  Put a new [JSONObject] and allows to insert data in it.
 */
fun JSONObject.putObject(key: String, block: JSONObject.() -> Unit) {
    put(key, JSONObject().apply(block))
}

/**
 *  Put a new [JSONArray] and allows to insert data in it.
 */
fun JSONObject.putArray(key: String, block: JSONArray.() -> Unit) {
    put(key, JSONArray().apply(block))
}


/**
 * Similar to [JSONArray.toJSONObject] but it uses the indexes of the array as the keys/names.
 */
fun JSONArray.asJSONObject(): JSONObject {
    val obj = JSONObject()
    for (i in 0 until length()) {
        obj.put(i.toString(), get(i))
    }
    return obj
}


/**
 * Iterate over all objects on a [JSONArray].
 *
 * Note: Objects of type [JSONArray] wrapped on the array will be converted into a [JSONObject]
 */
inline fun JSONArray.forEach(action: (JSONObject) -> Unit) {

    for (i in 0 until length()) {
        // Handling wrapped JSONArray converting them into a JSONObject.
        val o = optJSONObject(i) ?: optJSONArray(i)?.asJSONObject() ?: continue
        action(o)
    }
}


/**
 * Map a JSONObject into a data class.
 *
 * It'll only map arguments from the constructor of the class, the arguments name should match with the JSON keys.
 * Extremely recommended to use nullable arguments to handle not found keys in a JSON.
 *
 * @throws Exception Advise to catch any conversion error.
 */
@Experimental
inline fun <reified T : Any> JSONObject.mapInto(): T? {
    val constructor = T::class.primaryConstructor ?: return null
    val parameters = constructor.parameters

    val arguments = parameters.associateWith { opt(it.name) }

    return constructor.callBy(arguments)
}

/**
 * Map a JSONArray into a MutableList containing mapped data classes for every JSONObject inside.
 *
 * @see [JSONObject.mapInto]
 * @throws Exception Advise to catch any conversion error.
 */
@Experimental
inline fun <reified T : Any> JSONArray.mapIntoListOf(): MutableList<T>? {
    val list = mutableListOf<T>()
    forEach { list.add(it.mapInto<T>() ?: return null) }
    return list.takeUnless { it.isEmpty() }
}


/**
 * Iterator for [JSONArray]
 */
operator fun JSONArray.iterator(): Iterator<Any> = object : Iterator<Any> {

    private var index = 0

    override fun hasNext() = index < length()

    override fun next(): Any {
        val element = get(index)
        index++
        return element
    }
}