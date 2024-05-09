@file:JvmName("Views")

package com.reco1l.toolkt.android

import android.graphics.Outline
import android.graphics.drawable.ColorDrawable
import android.os.Build.VERSION_CODES
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.*
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.core.view.updateLayoutParams
import kotlin.math.min


// Drawables

/**
 * The view background color.
 */
var View.backgroundColor: Int?
    get() = (background as? ColorDrawable)?.color
    set(value) {

        if (value == null) {
            background = null
            return
        }

        (background as? ColorDrawable)?.apply { color = value } ?: run { background = ColorDrawable(value) }
    }

/**
 * The view foreground color.
 */
var View.foregroundColor: Int?
    @RequiresApi(VERSION_CODES.M) get() = (foreground as? ColorDrawable)?.color
    @RequiresApi(VERSION_CODES.M)
    set(value) {

        if (value == null) {
            foreground = null
            return
        }

        (foreground as? ColorDrawable)?.apply { color = value } ?: run { foreground = ColorDrawable(value) }
    }

/**
 * The view corner radius.
 * Internally uses a custom [ViewOutlineProvider] and it'll replace any previously set.
 *
 * @see RoundOutlineProvider
 */
var View.cornerRadius: Float
    get() = (outlineProvider as? RoundOutlineProvider)?.radius ?: 0f
    set(value) {

        val provider = outlineProvider as? RoundOutlineProvider ?: RoundOutlineProvider().also { outlineProvider = it }
        provider.radius = value

        invalidateOutline()
    }


// Position

/**
 * Returns the absolute position in the window.
 *
 * @see[View.getLocationInWindow]
 */
fun View.getAbsolutePosition(output: IntArray = IntArray(2)): IntArray {
    return output.also { getLocationInWindow(it) }
}


// Safe properties

/**
 * Ensures that the view will always have layout params, if not a new one with [WRAP_CONTENT] as
 * both width and height dimensions is set.
 */
fun View.ensureLayoutParams() {
    if (layoutParams == null) {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    }
}

/**
 * Ensures that the view will always have a unique ID, if not a random one is generated.
 */
fun View.ensureID() {
    if (id == NO_ID) {
        id = View.generateViewId()
    }
}


// Size

/**
 * Changes the view width.
 */
var View.layoutWidth
    get() = width
    set(value) {
        ensureLayoutParams()
        updateLayoutParams { width = value }
    }

/**
 * Changes the view height.
 */
var View.layoutHeight
    get() = height
    set(value) {
        ensureLayoutParams()
        updateLayoutParams { height = value }
    }

/**
 * Changes the view scale in both axis.
 */
fun View.setScale(scale: Float) {
    scaleX = scale
    scaleY = scale
}


// Attachment

/**
 * Remove self from a parent.
 */
fun View.removeSelf() {
    (parent as? ViewGroup)?.removeView(this)
}


// Round outline

/**
 * Applies a round outline to the view.
 */
class RoundOutlineProvider : ViewOutlineProvider() {

    /**
     * The corner radius.
     *
     * Note: You must [invalidate][View.invalidateOutline] the view outline in order to take effect on this.
     */
    var radius = 0f


    override fun getOutline(view: View, outline: Outline) {

        // Clipping view to outline, without this the rounding will not take effect.
        view.clipToOutline = true

        // This is a workaround for older devices without Skia support where if the radius is greater
        // than any of the view bounds it'll cause an unexpected visual.
        val radius = radius.coerceIn(0f, min(view.width, view.height) / 2f)

        // Applying corner radius to outline.
        outline.setRoundRect(0, 0, view.width, view.height, radius)
    }

}
