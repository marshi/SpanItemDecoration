package marshi.android.spanitemdecoration.decoration

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.annotation.DimenRes
import marshi.android.spanitemdecoration.BitmapDrawParameter
import marshi.android.spanitemdecoration.DecorationAsset
import marshi.android.spanitemdecoration.DrawParameter
import marshi.android.spanitemdecoration.SpanItemDecoration

private typealias BitMapDecorationAsset = DecorationAsset<Bitmap>

abstract class BitmapSpanItemDecoration(
    private val leftSpace: Float,
    private val paddingTop: Float,
    private val paddingBottom: Float
) : SpanItemDecoration<BitMapDecorationAsset>() {

    constructor(
        context: Context,
        @DimenRes leftSpaceDimenResId: Int,
        @DimenRes paddingTopDimenResId: Int,
        @DimenRes paddingBottomDimenResId: Int
    ) : this(
        context.resources.getDimension(leftSpaceDimenResId),
        context.resources.getDimension(paddingTopDimenResId),
        context.resources.getDimension(paddingBottomDimenResId)
    )

    override fun draw(canvas: Canvas, drawParameter: DrawParameter) {
        val parameter = drawParameter as? BitmapDrawParameter ?: return
        canvas.drawBitmap(parameter.bitmap, parameter.x, parameter.y, parameter.paint)
    }

    override fun drawParameter(
        position: Int,
        view: View,
        prevAsset: BitMapDecorationAsset?,
        asset: BitMapDecorationAsset,
        nextAsset: BitMapDecorationAsset?,
        paint: Paint?
    ): DrawParameter {
        var top = view.top.coerceAtLeast(0) + paddingTop
        if (nextAsset?.isEqualsTo(asset) == false) {
            top = top.coerceAtMost(view.bottom - asset.value.height - paddingBottom)
        }
        return BitmapDrawParameter(
            asset.value,
            leftSpace,
            top,
            paint
        )
    }
}
