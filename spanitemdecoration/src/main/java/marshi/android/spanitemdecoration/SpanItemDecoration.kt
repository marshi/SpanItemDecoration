package marshi.android.spanitemdecoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

abstract class SpanItemDecoration(
    context: Context,
    open val groupAdapter: RecyclerView.Adapter<*>
) : RecyclerView.ItemDecoration() {

    open val textPaddingTop = 0
    open val textSize = 0
    open val textPaddingBottom = 0
    open val textLeftSpace = 0

    open val paint = Paint().apply {
        style = Paint.Style.FILL
        textSize = 100f
        color = Color.BLACK
        isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var prevText: String? = null
        parent.children.forEachIndexed { index, view ->
            val position = parent.getChildAdapterPosition(view)
            val text = text(position) ?: return@forEachIndexed
            if (prevText == text) {
                return@forEachIndexed
            }
            prevText = text
            val nextText = text(position + 1)
            val drawParameter = drawParameter(position, view, prevText, text, nextText)
            draw(c, drawParameter)
        }
    }

    protected abstract fun text(position: Int): String?

    protected abstract fun draw(canvas: Canvas, drawParameter: DrawParameter)

    protected abstract fun drawParameter(
        position: Int,
        view: View,
        prevText: String?,
        text: String,
        nextText: String?
    ): DrawParameter
}