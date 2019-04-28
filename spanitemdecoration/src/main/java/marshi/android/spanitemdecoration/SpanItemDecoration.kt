package marshi.android.spanitemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.SparseArray
import android.view.View
import androidx.core.util.forEach
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
    // Keep SparseArray instance on property to avoid object creation in every onDrawOver()
    private val adapterPositionToViews = SparseArray<View>()

    val paint = Paint().apply {
        style = Paint.Style.FILL
        textSize = 100f
        color = Color.BLACK
        isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            if (position != RecyclerView.NO_POSITION && position < groupAdapter.itemCount) {
                adapterPositionToViews.put(position, view)
            }
        }

        var prevText: String? = null
        adapterPositionToViews.forEach { position, view ->
            val text = getSessionTime(position) ?: return@forEach
            if (prevText == text) return@forEach
            prevText = text
            val nextText = getSessionTime(position + 1)
            var textBaselineY = view.top.coerceAtLeast(0) + textPaddingTop + textSize
            if (text != nextText) {
                textBaselineY = textBaselineY.coerceAtMost(view.bottom - textPaddingBottom)
            }

            println("position ${text}, ${textBaselineY.toFloat()}")

            c.drawText(
                text,
                textLeftSpace.toFloat(),
                textBaselineY.toFloat(),
                paint
            )
        }

        adapterPositionToViews.clear()
    }

    private fun getSessionTime(position: Int): String? {
        if (position < 0 || position >= groupAdapter.itemCount) {
            return null
        }
        return "aaaaaaaaaaaaaaaa+${position / 3}"
    }
}
