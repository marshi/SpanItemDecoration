package marshi.android.spanitemdecoration.bitmap

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import marshi.android.spanitemdecoration.BitmapDrawParameter
import marshi.android.spanitemdecoration.DrawParameter
import marshi.android.spanitemdecoration.R
import marshi.android.spanitemdecoration.SpanItemDecoration

class BitmapSpanItemDecoration(
    val context: Context,
    override val groupAdapter: RecyclerView.Adapter<*>
) : SpanItemDecoration<BitMapDecorationAsset>(context, groupAdapter) {
    override fun asset(position: Int): BitMapDecorationAsset? {
        val bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.ic_launcher
        )
        return when (position / 2 % 2) {
            0 -> BitMapDecorationAsset(bitmap, R.drawable.ic_launcher.toString())
            1 -> BitMapDecorationAsset(bitmap, R.drawable.ic_launcher_round.toString())
            else -> BitMapDecorationAsset(bitmap, R.drawable.ic_launcher_round.toString())
        }
    }

    override fun draw(canvas: Canvas, drawParameter: DrawParameter) {
        val parameter = drawParameter as? BitmapDrawParameter ?: return
        canvas.drawBitmap(parameter.bitmap, parameter.x, parameter.y, parameter.paint)
    }

    override fun drawParameter(
        position: Int,
        view: View,
        prevAsset: BitMapDecorationAsset?,
        asset: BitMapDecorationAsset,
        nextAsset: BitMapDecorationAsset?
    ): DrawParameter {
        var top = view.top.coerceAtLeast(0)
        if (nextAsset?.isEqualsTo(asset) == false) {
            top = top.coerceAtMost(view.bottom - asset.bitmap.height)
        }
        return BitmapDrawParameter(
            position,
            asset.bitmap,
            0f,
            top.toFloat(),
            null
        )
    }
}
