package com.reco1l.toolkt.graphics

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.Options
import com.reco1l.toolkt.half
import java.io.File


// Options

/**
 * Calculates the dimensions of a image without loading a bitmap it into memory.
 *
 * @param file The file to get the dimensions from.
 */
fun Options.calculateDimensions(file: File): Options {
    // This is required to be true otherwise the bitmap will be loaded into memory when we only
    // want to know its dimensions.
    inJustDecodeBounds = true

    // This will only decode the dimensions of the image and the MIME type due to the condition above.
    BitmapFactory.decodeFile(file.absolutePath, this)

    return this
}


/**
 * Calculates the sample size for the current dimension, make sure to call [calculateDimensions] first.
 *
 * @see BitmapFactory.Options.inSampleSize
 */
fun Options.approximateSampleSize(targetWidth: Int, targetHeight: Int): Options {
    if (outWidth <= targetWidth && outHeight <= targetHeight)
        return this

    var size = 1

    while (outWidth.half / size >= targetHeight && outHeight.half / size >= targetHeight)
        size *= 2

    inSampleSize = size
    return this
}


/**
 * Decodes a file into a bitmap with this as options.
 */
fun Options.createBitmap(file: File): Bitmap = BitmapFactory.decodeFile(file.absolutePath, this)


// Bitmap

/**
 * Crops the bitmap in the center to the given dimensions.
 */
fun Bitmap.cropInCenter(targetWidth: Int, targetHeight: Int): Bitmap {
    val newX = (width - targetWidth) / 2
    val newY = (height - targetHeight) / 2

    if (newX < 0 || newY < 0)
        return this

    return Bitmap.createBitmap(this, newX, newY, targetWidth, targetHeight)
}

