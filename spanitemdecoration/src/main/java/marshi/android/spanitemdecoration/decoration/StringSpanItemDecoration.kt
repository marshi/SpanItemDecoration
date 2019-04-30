package marshi.android.spanitemdecoration.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import marshi.android.spanitemdecoration.DecorationAsset
import marshi.android.spanitemdecoration.DrawParameter
import marshi.android.spanitemdecoration.SpanItemDecoration
import marshi.android.spanitemdecoration.TextDrawParameter

private typealias StringDecorationAsset = DecorationAsset<String>

abstract class StringSpanItemDecoration(
    private val context: Context,
    private val textSizeSp: Float,
    private val textLeftSpaceSp: Float,
    private val textPaddingTopSp: Float,
    private val textPaddingBottomSp: Float,
    @ColorInt private val colorInt: Int
) : SpanItemDecoration<StringDecorationAsset>() {
    constructor(
        context: Context,
        @DimenRes textSizeDimenResId: Int,
        @DimenRes textLeftSpaceDimenResId: Int,
        @DimenRes textPaddingTopDimenResId: Int,
        @DimenRes textPaddingBottomDimenResId: Int,
        @ColorInt color: Int
    ) : this(
        context,
        context.resources.getDimension(textSizeDimenResId),
        context.resources.getDimension(textLeftSpaceDimenResId),
        context.resources.getDimension(textPaddingTopDimenResId),
        context.resources.getDimension(textPaddingBottomDimenResId),
        color
    )

    override fun draw(canvas: Canvas, p: DrawParameter) {
        val param = p as? TextDrawParameter ?: return
        canvas.drawText(
            param.text,
            param.x,
            param.y,
            param.paint
        )
    }

    override fun drawParameter(
        position: Int,
        view: View,
        prevAsset: StringDecorationAsset?,
        asset: StringDecorationAsset,
        nextAsset: StringDecorationAsset?,
        paint: Paint?
    ): DrawParameter {
        val paintParam = paint ?: Paint().apply {
            this.style = Paint.Style.FILL
            this.textSize = TypedValue.applyDimension(COMPLEX_UNIT_SP, textSizeSp, context.resources.displayMetrics)
            this.color = colorInt
            this.isAntiAlias = true
        }
        var textBaselineY = view.top.coerceAtLeast(0) + textPaddingTopSp + textSizeSp
        if (nextAsset?.isEqualsTo(asset) == false) {
            textBaselineY = textBaselineY.coerceAtMost(view.bottom - textPaddingBottomSp)
        }
        return TextDrawParameter(
            asset.value,
            textLeftSpaceSp,
            textBaselineY,
            paintParam
        )
    }
}
