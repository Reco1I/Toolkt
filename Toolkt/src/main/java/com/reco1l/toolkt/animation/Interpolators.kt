package com.reco1l.toolkt.animation

import android.animation.TimeInterpolator
import com.reco1l.toolkt.MathF
import com.reco1l.toolkt.half
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin


fun interface EasingFunction : TimeInterpolator {
    operator fun invoke(input: Float) = getInterpolation(input)
}


object TimeEasing {

    val LINEAR = EasingFunction { it }


    // Exponential

    val EXPO_OUT = EasingFunction { if (it == 1f) it else -(2f.pow(-10f * it)) + 1f }

    val EXPO_IN = EasingFunction { if (it == 0f) it else 2f.pow(10f * (it - 1)) - 0.001f }


    // Bounce

    val BOUNCE_OUT = EasingFunction {
        // org/andengine/util/modifier/ease/EaseBounceOut.java:54
        when {
            it < 1f / 2.75f -> 7.5625f * it.pow(2)

            it < 2f / 2.75f -> 7.5625f * (it - 1.5f / 2.75f).pow(2) + 0.75f

            it < 2.5f / 2.75f -> 7.5625f * (it - 2.25f / 2.75f).pow(2) + 0.9375f

            else -> 7.5625f * (it - 2.625f / 2.75f).pow(2) + 0.984375f
        }
    }

    val BOUNCE_IN = EasingFunction { 1f - BOUNCE_OUT.getInterpolation(1f - it) }

    // Sine

    val SINE_IN = EasingFunction { -cos(it * MathF.PI.half) + 1f }

    val SINE_OUT = EasingFunction { sin(it * MathF.PI.half) }


    // Acceleration

    val DECELERATE = EasingFunction { 1.0f - (1.0f - it).pow(2) }

    val ACCELERATE = EasingFunction { it.pow(2) }

}