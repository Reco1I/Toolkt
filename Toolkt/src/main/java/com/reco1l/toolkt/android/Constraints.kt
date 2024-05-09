@file:JvmName("Constraints")

package com.reco1l.toolkt.android

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams


/**
 * Returns the [ConstraintLayout.LayoutParams][LayoutParams] of this view. If the view does not have
 * a [ConstraintLayout.LayoutParams][LayoutParams] a new one is created (inherithing dimensions from
 * the current layout params) and set to this view.
 */
val View.constraintLayoutParams
    get() = layoutParams as? LayoutParams ?: LayoutParams(layoutParams).also { layoutParams = it }


var View.leftToLeft: View?
    get() = (parent as? ConstraintLayout)?.findViewById(constraintLayoutParams.leftToLeft)
    set(value) {
        constraintLayoutParams.leftToLeft = value?.id ?: View.NO_ID
    }

var View.leftToRight: View?
    get() = (parent as? ConstraintLayout)?.findViewById(constraintLayoutParams.leftToRight)
    set(value) {
        constraintLayoutParams.leftToRight = value?.id ?: View.NO_ID
    }

var View.topToTop: View?
    get() = (parent as? ConstraintLayout)?.findViewById(constraintLayoutParams.topToTop)
    set(value) {
        constraintLayoutParams.topToTop = value?.id ?: View.NO_ID
    }

var View.topToBottom: View?
    get() = (parent as? ConstraintLayout)?.findViewById(constraintLayoutParams.topToBottom)
    set(value) {
        constraintLayoutParams.topToBottom = value?.id ?: View.NO_ID
    }

var View.rightToRight: View?
    get() = (parent as? ConstraintLayout)?.findViewById(constraintLayoutParams.rightToRight)
    set(value) {
        constraintLayoutParams.rightToRight = value?.id ?: View.NO_ID
    }

var View.rightToLeft: View?
    get() = (parent as? ConstraintLayout)?.findViewById(constraintLayoutParams.rightToLeft)
    set(value) {
        constraintLayoutParams.rightToLeft = value?.id ?: View.NO_ID
    }

var View.bottomToBottom: View?
    get() = (parent as? ConstraintLayout)?.findViewById(constraintLayoutParams.bottomToBottom)
    set(value) {
        constraintLayoutParams.bottomToBottom = value?.id ?: View.NO_ID
    }

var View.bottomToTop: View?
    get() = (parent as? ConstraintLayout)?.findViewById(constraintLayoutParams.bottomToTop)
    set(value) {
        constraintLayoutParams.bottomToTop = value?.id ?: View.NO_ID
    }