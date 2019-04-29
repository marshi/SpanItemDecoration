package marshi.android.spanitemdecoration

import android.graphics.Bitmap
import android.graphics.Paint

sealed class DrawParameter

class TextDrawParameter(
    val text: String,
    val x: Float,
    val y: Float,
    val paint: Paint
) : DrawParameter()

class BitmapDrawParameter(
    val position: Int,
    val bitmap: Bitmap,
    val x: Float,
    val y: Float,
    val paint: Paint?
) : DrawParameter()
