package marshi.android.spanitemdecoration

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BitmapSpanItemDecoration(
    val context: Context,
    override val groupAdapter: RecyclerView.Adapter<*>
) : SpanItemDecoration<Bitmap>(context, groupAdapter) {
    override fun asset(position: Int): Bitmap? {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher)
        return bitmap
    }

    override fun draw(canvas: Canvas, drawParameter: DrawParameter) {
        val parameter = drawParameter as? BitmapDrawParameter ?: return
        canvas.drawBitmap(parameter.bitmap, parameter.x, parameter.y, parameter.paint)
    }

    override fun drawParameter(
        position: Int,
        view: View,
        prevAsset: Bitmap?,
        asset: Bitmap,
        nextAsset: Bitmap?
    ): DrawParameter {
        var top = view.top.coerceAtLeast(0)
        if (asset != nextAsset) {
            top = top.coerceAtMost(view.bottom - asset.height)
        }
        return BitmapDrawParameter(
            position,
            asset,
            0f,
            top.toFloat(),
            null
        )
    }
}
