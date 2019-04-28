package marshi.android.spanitemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TextSpanItemDecoration(
    context: Context,
    override val groupAdapter: RecyclerView.Adapter<*>
) : SpanItemDecoration<String>(
    context, groupAdapter
) {
    private val resources = context.resources
    private val textSize = resources.getDimensionPixelSize(
        R.dimen.session_bottom_sheet_left_time_text_size
    )
    private val textLeftSpace = resources.getDimensionPixelSize(
        R.dimen.session_bottom_sheet_left_time_text_left
    )
    private val textPaddingTop = resources.getDimensionPixelSize(
        R.dimen.session_bottom_sheet_left_time_text_padding_top
    )
    private val textPaddingBottom = resources.getDimensionPixelSize(
        R.dimen.session_bottom_sheet_left_time_text_padding_bottom
    )
    private val paint = Paint().apply {
        style = Paint.Style.FILL
        textSize = 100f
        color = Color.BLACK
        isAntiAlias = true
    }

    override fun text(position: Int): String? {
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
        prevText: String?,
        text: String,
        nextText: String?
    ): DrawParameter {
        var textBaselineY = view.top.coerceAtLeast(0) + textPaddingTop + textSize
        if (text != nextText) {
            textBaselineY = textBaselineY.coerceAtMost(view.bottom - textPaddingBottom)
        }
        return TextDrawParameter(
            text, textLeftSpace.toFloat(), textBaselineY.toFloat(), paint
        )
    }
}
