package marshi.android.spanitemdecoration

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import marshi.android.spanitemdecoration.string.StringDecorationAsset
import marshi.android.spanitemdecoration.string.TextSpanItemDecoration

class ConcreteTextSpanItemDecoration(
    context: Context,
    override val groupAdapter: RecyclerView.Adapter<*>,
    textSize: Int,
    textLeftSpace: Int,
    textPaddingTop: Int,
    textPaddingBottom: Int
) : TextSpanItemDecoration(context, groupAdapter, textSize, textLeftSpace, textPaddingTop, textPaddingBottom) {

    override fun asset(position: Int): StringDecorationAsset? {
        if (position < 0 || position >= groupAdapter.itemCount) {
            return null
        }
        val string = "aaaaaaaaaaaaaaaa+${position / 3}"
        return StringDecorationAsset(string, string)
    }
}