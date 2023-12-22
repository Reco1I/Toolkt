package com.reco1l.toolkt

import kotlin.math.pow
import kotlin.math.roundToInt


inline val Int.half get() = this / 2
inline val Int.twice get() = this * 2
inline val Int.quarter get() = this / 4

inline val Float.half get() = this / 2f
inline val Float.twice get() = this * 2f
inline val Float.quarter get() = this / 4f

inline val Double.half get() = this / 2.0
inline val Double.twice get() = this * 2.0
inline val Double.quarter get() = this / 4.0


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
