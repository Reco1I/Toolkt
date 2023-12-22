package com.reco1l.toolkt.graphics

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.Options
import com.reco1l.toolkt.half
import java.io.File


object BitmapUtils
{

    /**
     * Get the dimensions of a image without loading a bitmap it into memory.
     *
     * @param file The file to get the dimensions from.
     */
    fun calculateDimensions(file: File, options: Options = Options()) = options.run {

        // This is required to be true otherwise the bitmap will be loaded into memory when we only
        // want to know its dimensions.
        inJustDecodeBounds = true

        BitmapFactory.decodeFile(file.absolutePath, this)

        // At this point we should have the dimensions of the bitmap as well the MIME type.
        outWidth to outHeight
    }

    /**
     * Calculates the sample size for the given dimensions.
     *
     * @see BitmapFactory.Options.inSampleSize
     */
    fun calculateSampleSize(width: Int, height: Int, targetWidth: Int, targetHeight: Int): Int
    {
        if (width <= targetWidth && height <= targetHeight)
            return 1

        var size = 1

        while (width.half / size >= targetHeight && height.half / size >= targetHeight)
            size *= 2

        return size
    }

    /**
     * Decodes a file into a bitmap with given dimensions.
     */
    fun decodeFile(file: File, targetWidth: Int, targetHeight: Int, options: Options = Options()): Bitmap?
    {
        val dimensions = calculateDimensions(file, options)

        options.inSampleSize = calculateSampleSize(dimensions.first, dimensions.second, targetWidth, targetHeight)
        options.inJustDecodeBounds = false

        return BitmapFactory.decodeFile(file.absolutePath, options)
    }
}