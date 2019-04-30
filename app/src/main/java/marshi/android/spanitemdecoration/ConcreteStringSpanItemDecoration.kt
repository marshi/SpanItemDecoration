package marshi.android.spanitemdecoration

import android.content.Context
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import marshi.android.spanitemdecoration.string.StringSpanItemDecoration

class ConcreteStringSpanItemDecoration(
    context: Context,
    override val groupAdapter: RecyclerView.Adapter<*>,
    @DimenRes textSizeDimenId: Int,
    @DimenRes textLeftSpaceDimenId: Int,
    @DimenRes textPaddingTopDimenId: Int,
    @DimenRes textPaddingBottomDimenId: Int
) : StringSpanItemDecoration(
    context,
    groupAdapter,
    textSizeDimenId,
    textLeftSpaceDimenId,
    textPaddingTopDimenId,
    textPaddingBottomDimenId
) {

    override fun asset(position: Int): DecorationAsset<String>? {
        if (position < 0 || position >= groupAdapter.itemCount) {
            return null
        }
        val string = "aaaaaaaaaaaaaaaa+${position / 3}"
        return DecorationAsset(string, string)
    }
}
