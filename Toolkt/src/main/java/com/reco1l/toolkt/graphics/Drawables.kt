package com.reco1l.toolkt.graphics

import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.view.Gravity
import androidx.annotation.RequiresApi
import com.reco1l.toolkt.kotlin.runSafe


// ClipDrawable

/**
 * Wrap a drawable inside a [ClipDrawable].
 */
fun Drawable.clip(gravity: Int = Gravity.LEFT, orientation: Int = ClipDrawable.HORIZONTAL) = ClipDrawable(this, gravity, orientation)


// LayerDrawable

/**
 * Iterate over all layers.
 */
fun LayerDrawable.forEach(block: (Drawable) -> Unit) {
    for (i in 0 until numberOfLayers) {
        block(getDrawable(i))
    }
}

/**
 * Improved constructor of [LayerDrawable]
 */
fun LayerDrawable(vararg layers: Drawable) = LayerDrawable(layers)


// GradientDrawable

/**
 * Apply a radius to the specified anchor or all anchors.
 *
 * @param anchor The anchor to apply the radius, if `null` is passed the radius will be applied
 * to all anchors.
 */
fun GradientDrawable.setRadius(radius: Float, @CornerAnchor anchor: Int? = null) {
    val radii = FloatArray(8)

    if (anchor == null) {
        radii.fill(radius)
        cornerRadii = radii
        return
    }

    when (anchor) {
        Anchor.TOP_LEFT -> radii.fill(radius, 0, 2)
        Anchor.TOP_RIGHT -> radii.fill(radius, 2, 4)
        Anchor.BOTTOM_LEFT -> radii.fill(radius, 4, 6)
        Anchor.BOTTOM_RIGHT -> radii.fill(radius, 6, 8)
    }
    cornerRadii = radii
}