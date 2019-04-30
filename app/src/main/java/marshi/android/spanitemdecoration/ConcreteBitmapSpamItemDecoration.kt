package marshi.android.spanitemdecoration

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import marshi.android.spanitemdecoration.decoration.BitmapSpanItemDecoration

class ConcreteBitmapSpamItemDecoration(
    context: Context,
    private val groupAdapter: RecyclerView.Adapter<*>,
    @DimenRes leftSpace: Int,
    @DimenRes paddingTop: Int,
    @DimenRes paddingBottom: Int
) : BitmapSpanItemDecoration(
    context,
    leftSpace,
    paddingTop,
    paddingBottom
) {

    private val bitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_launcher
    )

    private val bitmapRound = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_launcher_round
    )

    override fun asset(position: Int): DecorationAsset<Bitmap>? {
        if (position < 0 || position >= groupAdapter.itemCount) {
            return null
        }

        return when (position / 2 % 2) {
            0 -> DecorationAsset(bitmap, R.drawable.ic_launcher.toString())
            1 -> DecorationAsset(bitmapRound, R.drawable.ic_launcher_round.toString())
            else -> DecorationAsset(bitmap, R.drawable.ic_launcher_round.toString())
        }
    }
}
