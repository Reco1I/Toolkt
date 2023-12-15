@file:JvmName("Margins")

package com.reco1l.toolkt.android

import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewGroup.MarginLayoutParams


/**
 * Ensures that the current layout params are of type [MarginLayoutParams].
 */
fun View.ensureMarginParams()
{
    if (layoutParams !is MarginLayoutParams)
        layoutParams = layoutParams
            ?.let { MarginLayoutParams(it) }
            ?: MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT)
}

/**
 * Change View margins, if the View hasn't a [MarginLayoutParams] it'll be applied.
 */
fun View.setMargins(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
)
{
    ensureMarginParams()

    (layoutParams as MarginLayoutParams).apply {

        if (top != null)
            topMargin = top

        if (bottom != null)
            bottomMargin = bottom

        if (left != null)
            leftMargin = left

        if (right != null)
            rightMargin = right

        requestLayout()
    }
}

fun View.setMargins(
    left: Float? = null,
    top: Float? = null,
    right: Float? = null,
    bottom: Float? = null

) = setMargins(
    left = left?.toInt(),
    top = top?.toInt(),
    right = right?.toInt(),
    bottom = bottom?.toInt()
)