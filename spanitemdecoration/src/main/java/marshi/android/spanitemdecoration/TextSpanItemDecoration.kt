package marshi.android.spanitemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TextSpanItemDecoration(
    context: Context,
    override val groupAdapter: RecyclerView.Adapter<*>,
    private val textSize: Int,
    private val textLeftSpace: Int,
    private val textPaddingTop: Int,
    private val textPaddingBottom: Int,
    private val paint: Paint = Paint().apply {
        this.style = Paint.Style.FILL
        this.textSize = 100f
        this.color = Color.BLACK
        this.isAntiAlias = true
    }
) : SpanItemDecoration<String>(
    context, groupAdapter
) {
    override fun asset(position: Int): String? {
        if (position < 0 || position >= groupAdapter.itemCount) {
            return null
        }
        return "aaaaaaaaaaaaaaaa+${position / 3}"
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
        prevAsset: String?,
        asset: String,
        nextAsset: String?
    ): DrawParameter {
        var textBaselineY = view.top.coerceAtLeast(0) + textPaddingTop + textSize
        if (asset != nextAsset) {
            textBaselineY = textBaselineY.coerceAtMost(view.bottom - textPaddingBottom)
        }
        return TextDrawParameter(
            asset,
            textLeftSpace.toFloat(),
            textBaselineY.toFloat(),
            paint
        )
    }
}
