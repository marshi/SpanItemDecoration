package marshi.android.spanitemdecoration

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

abstract class SpanItemDecoration<T : DecorationAsset<*>>(
    context: Context,
    open val groupAdapter: RecyclerView.Adapter<*>
) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        var prevAsset: T? = null
        parent.children.forEachIndexed { index, view ->
            val position = parent.getChildAdapterPosition(view)
            val currentAsset = asset(position) ?: return@forEachIndexed
            if (prevAsset?.isEqualsTo(currentAsset) == true) {
                println("equals to $position ${prevAsset?.id} ${currentAsset.id}")
                return@forEachIndexed
            }
            println("not equals to $position ${prevAsset?.id} ${currentAsset.id}")
            prevAsset = currentAsset
            val nextAsset = asset(position + 1)
            val drawParameter = drawParameter(position, view, prevAsset, currentAsset, nextAsset)
            draw(c, drawParameter)
        }
    }

    protected abstract fun asset(position: Int): T?

    protected abstract fun draw(canvas: Canvas, drawParameter: DrawParameter)

    protected abstract fun drawParameter(
        position: Int,
        view: View,
        prevAsset: T?,
        asset: T,
        nextAsset: T?
    ): DrawParameter
}
