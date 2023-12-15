@file:JvmName("Views")

package com.reco1l.toolkt.android

import android.graphics.Outline
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.*
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewOutlineProvider
import androidx.annotation.ColorInt
import kotlin.math.min


// Drawables

var View.backgroundColor: Int?
    get() = (background as? ColorDrawable)?.color
    set(value)
    {
        if (value != null)
            setBackgroundColor(value)
        else
            background = null
    }

var View.foregroundColor: Int?
    get() = (foreground as? ColorDrawable)?.color
    set(value)
    {
        if (value != null)
            setForegroundColor(value)
        else
            background = null
    }

fun View.setForegroundColor(@ColorInt color: Int)
{
    var drawable: ColorDrawable? = background as? ColorDrawable

    if (drawable != null)
    {
        drawable = drawable.mutate() as ColorDrawable
        drawable.color = color
    }
    else drawable = ColorDrawable(color)

    foreground = drawable
}


// Corner radius

var View.cornerRadius: Float
    /**
     * Return the view corner radius.
     */
    get() = (outlineProvider as? RoundOutlineProvider)?.radius ?: 0f
    /**
     * Set the View corner radius.
     *
     * Note: This will replace any previous outline provider with a [RoundOutlineProvider].
     */
    set(value)
    {
        var provider = outlineProvider as? RoundOutlineProvider

        if (value <= 0 && provider == null)
            return

        if (provider == null)
        {
            provider = RoundOutlineProvider()
            outlineProvider = provider
        }

        provider.radius = value
        invalidateOutline()
    }


// Position

/**
 * @see[View.getLocationInWindow]
 */
val View.absolutePosition: IntArray
    get() = IntArray(2).apply { getLocationInWindow(this) }


// Safe properties

/**
 * Ensures that the view has layout params, if not a layout params with [WRAP_CONTENT] as
 * dimensions is set.
 */
fun View.ensureLayoutParams()
{
    if (layoutParams == null)
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
}

/**
 * Ensures that the view has an ID, if not a generated ID is set.
 */
fun View.ensureID()
{
    if (id == NO_ID)
        id = View.generateViewId()
}


// Size

fun View.setSize(width: Float? = null, height: Float? = null) = setSize(
    width = width?.toInt(),
    height = height?.toInt()
)

/**
 * Set the width and height of the view with defaults.
 */
fun View.setSize(width: Int? = null, height: Int? = null)
{
    ensureLayoutParams()

    layoutParams.apply {

        if (width != null)
            this.width = width

        if (height != null)
            this.height = height

        requestLayout()
    }
}

fun View.setScale(scale: Float)
{
    scaleX = scale
    scaleY = scale
}


// Attachment

infix fun <T : View> T.attachTo(parent: ViewGroup): T
{
    parent.addView(this)
    return this
}

/**
 * Remove self from a parent.
 */
fun View.removeSelf()
{
    (parent as? ViewGroup)?.removeView(this)
}


// Execution

fun View.doPost(block: View.() -> Unit) = post { block() }



// Round outline

/**
 * Applies a round outline to the view.
 */
class RoundOutlineProvider : ViewOutlineProvider()
{

    /**
     * Horizontal outline offset.
     *
     * Note: You must [invalidate][View.invalidateOutline] the view outline in order to take effect on this.
     */
    var offsetX = 0

    /**
     * Vertical outline offset.
     *
     * Note: You must [invalidate][View.invalidateOutline] the view outline in order to take effect on this.
     */
    var offsetY = 0

    /**
     * The corner radius.
     *
     * Note: You must [invalidate][View.invalidateOutline] the view outline in order to take effect on this.
     */
    var radius: Float = 18f


    override fun getOutline(view: View, outline: Outline)
    {
        // Clipping view to outline, without this the rounding will not take effect.
        view.clipToOutline = true

        val width = view.width
        val height = view.height

        // This is a workaround for older devices without Skia support where if the radius is greater
        // than any of the view bounds it'll cause an unexpected visual.
        val radius = radius.coerceIn(0f, min(width, height) / 2f)

        // Applying corner radius to outline.
        outline.setRoundRect(0, 0, width, height, radius)

        // Applying offset to outline.
        outline.offset(offsetX, offsetY)
    }
}
