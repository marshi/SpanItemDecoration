package marshi.android.spanitemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class SpanItemDecoration(
    context: Context,
    val groupAdapter: RecyclerView.Adapter<*>
) : RecyclerView.ItemDecoration() {
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
    val paint = Paint().apply {
        style = Paint.Style.FILL
        textSize = 100f
        color = Color.BLACK
        isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var prevText: String? = null
        parent.children.forEachIndexed { index, view ->
            val position = parent.getChildAdapterPosition(view)
            val text = getSessionTime(position) ?: return@forEachIndexed
            if (prevText == text) {
                return@forEachIndexed
            }
            prevText = text
            val nextText = getSessionTime(position + 1)
            var textBaselineY = view.top.coerceAtLeast(0) + textPaddingTop + textSize
            if (text != nextText) {
                textBaselineY = textBaselineY.coerceAtMost(view.bottom - textPaddingBottom)
            }
            c.drawText(
                text,
                textLeftSpace.toFloat(),
                textBaselineY.toFloat(),
                paint
            )
        }
    }

    private fun getSessionTime(position: Int): String? {
        if (position < 0 || position >= groupAdapter.itemCount) {
            return null
        }
        return "aaaaaaaaaaaaaaaa+${position / 3}"
    }
}
