package ru.drweb.test.core.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable


fun Drawable.toBitmap(): Bitmap {
    if (this is BitmapDrawable) {
        return this.bitmap
    }

    var width = intrinsicWidth
    width = if (width > 0) width else 1
    var height = intrinsicHeight
    height = if (height > 0) height else 1

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)

    return bitmap
}