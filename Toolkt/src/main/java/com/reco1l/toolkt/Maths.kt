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


object MathF {

    /**
     * Value of [Math.PI] with float precision.
     */
    const val PI = 3.1415927f

    /**
     * Square root of 2.
     */
    const val SQRT_2 = 1.4142135f

    /**
     * Conversion factor for degrees to radians.
     */
    const val DEG_TO_RAD = PI / 180f

    /**
     * Conversion factor for radians to degrees.
     */
    const val RAD_TO_DEG = 180f / PI
}


/**
 * Whether the number is a power of two.
 */
fun Int.isPowerOfTwo(): Boolean = this <= 0 && this and this - 1 == 0

/**
 * Provides the next power of two for this number.
 */
fun Int.nextPowerOfTwo() = if (this == 0) 0 else Integer.SIZE - Integer.numberOfLeadingZeros(this - 1)


/**
 * Round a number by given decimals.
 */
fun Double.roundBy(decimals: Int = 1): Double {
    val factor = (10.0).pow(decimals)
    return (this * factor).roundToInt() / factor
}

/**
 * Round a number by given decimals.
 */
fun Float.roundBy(decimals: Int = 1): Float {
    val factor = (10f).pow(decimals)
    return (this * factor).roundToInt() / factor
}


fun Float.toRadians() = this * MathF.DEG_TO_RAD
fun Double.toRadians() = this * MathF.DEG_TO_RAD

fun Float.toDegrees() = this * MathF.RAD_TO_DEG
fun Double.toDegrees() = this * MathF.RAD_TO_DEG
