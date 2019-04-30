package marshi.android.spanitemdecoration

import android.graphics.Canvas
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

abstract class SpanItemDecoration<T : DecorationAsset<*>> : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var prevAsset: T? = null
        parent.children.forEachIndexed { _, view ->
            val position = parent.getChildAdapterPosition(view)
            val currentAsset = asset(position) ?: return@forEachIndexed
            if (prevAsset?.isEqualsTo(currentAsset) == true) {
                return@forEachIndexed
            }
            prevAsset = currentAsset
            val nextAsset = asset(position + 1)
            val drawParameter = drawParameter(position, view, prevAsset, currentAsset, nextAsset)
            draw(c, drawParameter)
        }
    }

    abstract fun asset(position: Int): T?

    internal abstract fun draw(canvas: Canvas, drawParameter: DrawParameter)

    internal abstract fun drawParameter(
        position: Int,
        view: View,
        prevAsset: T?,
        asset: T,
        nextAsset: T?
    ): DrawParameter
}
