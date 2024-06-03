@file:JvmName("Dimensions")

package com.reco1l.toolkt.android

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import androidx.core.view.updateMargins
import androidx.core.view.updatePadding
import kotlin.math.max


/// Margin

/**
 * Returns the [MarginLayoutParams] of this view. If the view does not have a [MarginLayoutParams]
 * a new one is created (inherithing dimensions from the current layout params) and set to this view.
 */
val View.marginLayoutParams
    get() = layoutParams as? MarginLayoutParams ?: MarginLayoutParams(layoutParams)


var View.margin
    get() = maxOf(topMargin, bottomMargin, leftMargin, rightMargin)
    set(value) {
        marginLayoutParams.topMargin = value
        marginLayoutParams.bottomMargin = value
        marginLayoutParams.leftMargin = value
        marginLayoutParams.rightMargin = value
    }

var View.verticalMargin
    get() = max(topMargin, bottomMargin)
    set(value) {
        marginLayoutParams.topMargin = value
        marginLayoutParams.bottomMargin = value
    }

var View.horizontalMargin
    get() = max(leftMargin, rightMargin)
    set(value) {
        marginLayoutParams.leftMargin = value
        marginLayoutParams.rightMargin = value
    }

var View.topMargin
    get() = marginTop
    set(value) {
        marginLayoutParams.topMargin = value
    }

var View.bottomMargin
    get() = marginBottom
    set(value) {
        marginLayoutParams.bottomMargin = value
    }

var View.leftMargin
    get() = marginLeft
    set(value) {
        marginLayoutParams.leftMargin = value
    }

var View.rightMargin
    get() = marginRight
    set(value) {
        marginLayoutParams.rightMargin = value
    }


/// Padding

var View.padding
    get() = maxOf(topPadding, bottomPadding, leftPadding, rightPadding)
    set(value) = setPadding(value)

var View.verticalPadding
    get() = max(topPadding, bottomPadding)
    set(value) = updatePadding(top = value, bottom = value)

var View.horizontalPadding
    get() = max(leftPadding, rightPadding)
    set(value) = updatePadding(left = value, right = value)

var View.topPadding
    get() = paddingTop
    set(value) = updatePadding(top = value)

var View.bottomPadding
    get() = paddingBottom
    set(value) = updatePadding(bottom = value)

var View.leftPadding
    get() = paddingLeft
    set(value) = updatePadding(left = value)

var View.rightPadding
    get() = paddingRight
    set(value) = updatePadding(right = value)


/// Typed values

/**
 * Converts a [Float] value to pixels using [TypedValue.applyDimension].
 *
 * @see COMPLEX_UNIT_DIP
 */
val Float.dp
    get() = TypedValue.applyDimension(COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

/**
 * Converts an [Int] value to pixels using [TypedValue.applyDimension].
 *
 * @see COMPLEX_UNIT_DIP
 */
val Int.dp
    get() = TypedValue.applyDimension(COMPLEX_UNIT_DIP, toFloat(), Resources.getSystem().displayMetrics).toInt()


/**
 * Converts a [Float] value to pixels using [TypedValue.applyDimension].
 *
 * @see COMPLEX_UNIT_DIP
 */
fun Float.dp(context: Context): Float {
    return TypedValue.applyDimension(COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)
}

/**
 * Converts an [Int] value to pixels using [TypedValue.applyDimension].
 *
 * @see COMPLEX_UNIT_DIP
 */
fun Int.dp(context: Context): Int {
    return TypedValue.applyDimension(COMPLEX_UNIT_DIP, toFloat(), context.resources.displayMetrics).toInt()
}

/**
 * Converts a [Float] value to scaled pixels using [TypedValue.applyDimension].
 *
 * @see COMPLEX_UNIT_SP
 */
val Float.sp
    get() = TypedValue.applyDimension(COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)

/**
 * Converts an [Int] value to scaled pixels using [TypedValue.applyDimension].
 *
 * @see COMPLEX_UNIT_SP
 */
val Int.sp
    get() = TypedValue.applyDimension(COMPLEX_UNIT_SP, toFloat(), Resources.getSystem().displayMetrics).toInt()


/**
 * Converts a [Float] value to scaled pixels using [TypedValue.applyDimension].
 *
 * @see COMPLEX_UNIT_SP
 */
fun Float.sp(context: Context): Float {
    return TypedValue.applyDimension(COMPLEX_UNIT_SP, this, context.resources.displayMetrics)
}

/**
 * Converts an [Int] value to scaled pixels using [TypedValue.applyDimension].
 *
 * @see COMPLEX_UNIT_SP
 */
fun Int.sp(context: Context): Int {
    return TypedValue.applyDimension(COMPLEX_UNIT_SP, toFloat(), context.resources.displayMetrics).toInt()
}