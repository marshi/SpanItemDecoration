package marshi.android.spanitemdecoration.string

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import marshi.android.spanitemdecoration.DrawParameter
import marshi.android.spanitemdecoration.SpanItemDecoration
import marshi.android.spanitemdecoration.TextDrawParameter

class TextSpanItemDecoration(
    context: Context,
    override val groupAdapter: RecyclerView.Adapter<*>,
    private val textSize: Int,
    private val textLeftSpace: Int,
    private val textPaddingTop: Int,
    private val textPaddingBottom: Int,
    private val paint: Paint = Paint().apply {
        this.style = Paint.Style.FILL
        this.textSize = textSize.toFloat()
        this.color = Color.BLACK
        this.isAntiAlias = true
    }
) : SpanItemDecoration<StringDecorationAsset>(
    context, groupAdapter
) {
    override fun asset(position: Int): StringDecorationAsset? {
        if (position < 0 || position >= groupAdapter.itemCount) {
            return null
        }
        val string = "aaaaaaaaaaaaaaaa+${position / 3}"
        return StringDecorationAsset(string, string)
    }

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
        println("draw $position ${asset.string}")
        var textBaselineY = view.top.coerceAtLeast(0) + textPaddingTop + textSize
        if (nextAsset?.isEqualsTo(asset) == false) {
            textBaselineY = textBaselineY.coerceAtMost(view.bottom - textPaddingBottom)
        }
        return TextDrawParameter(
            asset.string,
            textLeftSpace.toFloat(),
            textBaselineY.toFloat(),
            paint
        )
    }
}
