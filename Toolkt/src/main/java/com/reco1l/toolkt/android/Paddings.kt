@file:JvmName("Paddings")

package com.reco1l.toolkt.android

import android.view.View

/**
 * Change View paddings.
 */
fun View.setPaddings(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom

) = setPadding(left, top, right, bottom)


/**
 * Change View paddings.
 */
fun View.setPaddings(
    left: Float = paddingLeft.toFloat(),
    top: Float = paddingTop.toFloat(),
    right: Float = paddingRight.toFloat(),
    bottom: Float = paddingBottom.toFloat()

) = setPaddings(
    left = left.toInt(),
    top = top.toInt(),
    right = right.toInt(),
    bottom = bottom.toInt()
)