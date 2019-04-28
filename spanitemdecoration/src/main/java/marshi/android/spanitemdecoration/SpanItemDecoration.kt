package marshi.android.spanitemdecoration

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

abstract class SpanItemDecoration<T>(
    context: Context,
    open val groupAdapter: RecyclerView.Adapter<*>
) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var prevAsset: T? = null
        parent.children.forEachIndexed { index, view ->
            val position = parent.getChildAdapterPosition(view)
            val currentAsset = text(position) ?: return@forEachIndexed
            if (prevAsset == currentAsset) {
                return@forEachIndexed
            }
            prevAsset = currentAsset
            val nextAsset = text(position + 1)
            val drawParameter = drawParameter(position, view, prevAsset, currentAsset, nextAsset)
            draw(c, drawParameter)
        }
    }

    protected abstract fun text(position: Int): T?

    protected abstract fun draw(canvas: Canvas, drawParameter: DrawParameter)

    protected abstract fun drawParameter(
        position: Int,
        view: View,
        prevText: T?,
        text: T,
        nextText: T?
    ): DrawParameter
}
