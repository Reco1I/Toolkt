package com.reco1l.toolkt.kotlin

import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty0
import kotlin.reflect.full.createInstance


// Instantiation

/**
 * Create a new instance with the given parameters.
 */
fun <T : Any> KClass<T>.createInstance(vararg parameterValues: Any?): T {

    if (parameterValues.isEmpty()) {
        return createInstance()
    }

    val constructor = constructors.find { it.parameters.size == parameterValues.size }
    if (constructor == null) {
        throw IllegalArgumentException("No constructor matches the given parameters for class: $this")
    }

    val parameters = constructor.parameters
    val associativeMap = mutableMapOf<KParameter, Any?>()

    for (i in parameterValues.indices) {
        associativeMap[parameters[i]] = parameterValues[i]
    }

    return constructor.callBy(associativeMap)
}


// Properties

/**
 * Whether the property is a lazy initialized and it's already initialized.
 */
val KProperty0<*>.isLazyInitialized: Boolean
    get() = (getDelegate() as? Lazy<*>)?.isInitialized() ?: true


/**
 * Sets the value of a property by its name.
 *
 * If the property is not accessible it'll be made it accessible forcefully.
 * @see [Class.getDeclaredField]
 */
inline fun <T : Any, reified V : Any?> T.setField(name: String, value: V) {
    val field = javaClass.getDeclaredField(name)
    if (!field.isAccessible) {
        field.isAccessible = true
    }
    field.set(this, value)
}

/**
 * Gets the value of a property by its name.
 *
 * If the property is not accessible it'll be made it accessible forcefully.
 * @see [Class.getDeclaredField]
 */
inline fun <T : Any, reified V : Any?> T.getField(name: String): V {
    val field = javaClass.getDeclaredField(name)
    if (!field.isAccessible) {
        field.isAccessible = true
    }
    return field.get(this) as V
}


// Methods

/**
 * Invokes a method by its name.
 *
 * If the method is not accessible it'll be made it accessible forcefully.
 * @see [Class.getDeclaredMethod]
 */
fun <T : Any> T.invokeMethod(name: String, vararg parameters: Any) {
    val method = javaClass.getDeclaredMethod(name, *parameters.map { it.javaClass }.toTypedArray())
    if (!method.isAccessible) {
        method.isAccessible = true
    }
    method.invoke(this, *parameters)
}



