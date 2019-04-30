package marshi.android.spanitemdecoration

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import marshi.android.spanitemdecoration.string.StringSpanItemDecoration

class ConcreteStringSpanItemDecoration(
    context: Context,
    override val groupAdapter: RecyclerView.Adapter<*>,
    textSize: Int,
    textLeftSpace: Int,
    textPaddingTop: Int,
    textPaddingBottom: Int
) : StringSpanItemDecoration(
    context,
    groupAdapter,
    textSize,
    textLeftSpace,
    textPaddingTop,
    textPaddingBottom
) {

    override fun asset(position: Int): DecorationAsset<String>? {
        if (position < 0 || position >= groupAdapter.itemCount) {
            return null
        }
        val string = "aaaaaaaaaaaaaaaa+${position / 3}"
        return DecorationAsset(string, string)
    }
}
