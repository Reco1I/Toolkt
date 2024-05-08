@file:JvmName("Margins")

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


/// Margin

val View.marginLayoutParams
    get() = layoutParams as? MarginLayoutParams ?: MarginLayoutParams(layoutParams)


var View.topMargin
    get() = marginTop
    set(value) {
        marginLayoutParams.updateMargins(top = value)
    }

var View.bottomMargin
    get() = marginBottom
    set(value) {
        marginLayoutParams.updateMargins(bottom = value)
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

var View.topPadding
    get() = paddingTop
    set(value) {
        updatePadding(top = value)
    }

var View.bottomPadding
    get() = paddingBottom
    set(value) {
        updatePadding(bottom = value)
    }

var View.leftPadding
    get() = paddingLeft
    set(value) {
        updatePadding(left = value)
    }

var View.rightPadding
    get() = paddingRight
    set(value) {
        updatePadding(right = value)
    }


/// Typed values

val Float.dp
    get() = TypedValue.applyDimension(COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

fun Float.dp(context: Context): Float {
    return TypedValue.applyDimension(COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)
}


val Float.sp
    get() = TypedValue.applyDimension(COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)

fun Float.sp(context: Context): Float {
    return TypedValue.applyDimension(COMPLEX_UNIT_SP, this, context.resources.displayMetrics)
}