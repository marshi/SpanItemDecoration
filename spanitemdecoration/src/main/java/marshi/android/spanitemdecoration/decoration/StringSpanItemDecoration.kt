package marshi.android.spanitemdecoration.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.View
import androidx.annotation.DimenRes
import marshi.android.spanitemdecoration.DecorationAsset
import marshi.android.spanitemdecoration.DrawParameter
import marshi.android.spanitemdecoration.SpanItemDecoration
import marshi.android.spanitemdecoration.TextDrawParameter

private typealias StringDecorationAsset = DecorationAsset<String>

abstract class StringSpanItemDecoration(
    context: Context,
    private val textSize: Float,
    private val textLeftSpace: Float,
    private val textPaddingTop: Float,
    private val textPaddingBottom: Float,
    private val paint: Paint = Paint().apply {
        this.style = Paint.Style.FILL
        this.textSize = TypedValue.applyDimension(COMPLEX_UNIT_SP, textSize, context.resources.displayMetrics)
        this.color = Color.BLACK
        this.isAntiAlias = true
    }
) : SpanItemDecoration<StringDecorationAsset>() {
    constructor(
        context: Context,
        @DimenRes textSizeDimenResId: Int,
        @DimenRes textLeftSpaceDimenResId: Int,
        @DimenRes textPaddingTopDimenResId: Int,
        @DimenRes textPaddingBottomDimenResId: Int,
        paint: Paint
    ) : this(
        context,
        context.resources.getDimension(textSizeDimenResId),
        context.resources.getDimension(textLeftSpaceDimenResId),
        context.resources.getDimension(textPaddingTopDimenResId),
        context.resources.getDimension(textPaddingBottomDimenResId),
        paint
    )

    constructor(
        context: Context,
        @DimenRes textSizeDimenResId: Int,
        @DimenRes textLeftSpaceDimenResId: Int,
        @DimenRes textPaddingTopDimenResId: Int,
        @DimenRes textPaddingBottomDimenResId: Int
    ) : this(
        context,
        context.resources.getDimension(textSizeDimenResId),
        context.resources.getDimension(textLeftSpaceDimenResId),
        context.resources.getDimension(textPaddingTopDimenResId),
        context.resources.getDimension(textPaddingBottomDimenResId)
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
        nextAsset: StringDecorationAsset?
    ): DrawParameter {
        var textBaselineY = view.top.coerceAtLeast(0) + textPaddingTop + textSize
        if (nextAsset?.isEqualsTo(asset) == false) {
            textBaselineY = textBaselineY.coerceAtMost(view.bottom - textPaddingBottom)
        }
        return TextDrawParameter(
            asset.value,
            textLeftSpace,
            textBaselineY,
            paint
        )
    }
}
