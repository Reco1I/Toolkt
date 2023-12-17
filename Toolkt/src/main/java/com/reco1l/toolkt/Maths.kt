package com.reco1l.toolkt

import kotlin.math.pow
import kotlin.math.roundToInt


object MathF
{
    /**
     * Value of [Math.PI] with float precision.
     */
    const val PI = 3.1415927f

    /**
     * Half of [PI].
     */
    const val PI_HALF = PI / 2f

    /**
     * Two times [PI]
     */
    const val PI_TWICE = PI * 2f
}


/**
 * @return `true` if it's power of two.
 */
fun Int.isPowerOfTwo(): Boolean = this <= 0 && this and this - 1 == 0


/**
 * Round a number by given decimals.
 */
fun Double.roundBy(decimals: Int = 1): Double
{
    val factor = (10.0).pow(decimals)
    return (this * factor).roundToInt() / factor
}

/**
 * Round a number by given decimals.
 */
fun Float.roundBy(decimals: Int = 1): Float
{
    val factor = (10f).pow(decimals)
    return (this * factor).roundToInt() / factor
}
