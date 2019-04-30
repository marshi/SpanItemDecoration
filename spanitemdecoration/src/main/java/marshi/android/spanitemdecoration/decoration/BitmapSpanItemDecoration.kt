package marshi.android.spanitemdecoration.decoration

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import marshi.android.spanitemdecoration.BitmapDrawParameter
import marshi.android.spanitemdecoration.DecorationAsset
import marshi.android.spanitemdecoration.DrawParameter
import marshi.android.spanitemdecoration.SpanItemDecoration

private typealias BitMapDecorationAsset = DecorationAsset<Bitmap>

abstract class BitmapSpanItemDecoration(
    val context: Context
) : SpanItemDecoration<BitMapDecorationAsset>() {

    override fun draw(canvas: Canvas, drawParameter: DrawParameter) {
        val parameter = drawParameter as? BitmapDrawParameter ?: return
        canvas.drawBitmap(parameter.bitmap, parameter.x, parameter.y, parameter.paint)
    }

    override fun drawParameter(
        position: Int,
        view: View,
        prevAsset: BitMapDecorationAsset?,
        asset: BitMapDecorationAsset,
        nextAsset: BitMapDecorationAsset?
    ): DrawParameter {
        var top = view.top.coerceAtLeast(0)
        if (nextAsset?.isEqualsTo(asset) == false) {
            top = top.coerceAtMost(view.bottom - asset.value.height)
        }
        return BitmapDrawParameter(
            asset.value,
            0f,
            top.toFloat(),
            null
        )
    }
}
