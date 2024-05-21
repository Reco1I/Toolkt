@file:JvmName("Texts")

package com.reco1l.toolkt.android

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.getSystemService


/**
 * The current [Typeface] style.
 */
var TextView.fontStyle
    get() = typeface.style
    set(value) = setTypeface(typeface, value)

/**
 * The current font color.
 */
var TextView.fontColor: Int
    get() = currentTextColor
    set(value) = setTextColor(value)

/**
 * The current font size in pixels, see [TypedValue.COMPLEX_UNIT_PX].
 */
var TextView.fontSize: Float
    get() = textSize
    set(value) {
        textSize = value
    }

/**
 * The current [Typeface].
 */
var TextView.font: Typeface
    get() = typeface
    set(value) = setTypeface(value, fontStyle)



/// Input

/**
 * Forces the [InputMethodManager] to hide the virtual keyboard if it's showing along with clearing
 * focus in this [EditText].
 *
 * @see InputMethodManager.hideSoftInputFromWindow
 * @see EditText.clearFocus
 */
fun EditText.hideKeyboard() {
    clearFocus()
    context?.getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(windowToken, 0)
}


/// Drawables

var TextView.drawableLeft: Drawable?
    get() = compoundDrawables[0]
    set(value) {
        setCompoundDrawablesWithIntrinsicBounds(value, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3])
    }

var TextView.drawableTop: Drawable?
    get() = compoundDrawables[1]
    set(value) {
        setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], value, compoundDrawables[2], compoundDrawables[3])
    }

var TextView.drawableRight: Drawable?
    get() = compoundDrawables[2]
    set(value) {
        setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], value, compoundDrawables[3])
    }

var TextView.drawableBottom: Drawable?
    get() = compoundDrawables[3]
    set(value) {
        setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], value)
    }