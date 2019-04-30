package marshi.android.spanitemdecoration

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import marshi.android.spanitemdecoration.bitmap.BitmapSpanItemDecoration

class ConcreteBitmapSpamItemDecoration(
    context: Context,
    override val groupAdapter: RecyclerView.Adapter<*>
) : BitmapSpanItemDecoration(context, groupAdapter) {

    override fun asset(position: Int): DecorationAsset<Bitmap>? {
        val bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.ic_launcher
        )
        return when (position / 2 % 2) {
            0 -> DecorationAsset(bitmap, R.drawable.ic_launcher.toString())
            1 -> DecorationAsset(bitmap, R.drawable.ic_launcher_round.toString())
            else -> DecorationAsset(bitmap, R.drawable.ic_launcher_round.toString())
        }
    }
}
