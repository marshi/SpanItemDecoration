package marshi.android.spanitemdecoration.bitmap

import android.graphics.Bitmap
import marshi.android.spanitemdecoration.DecorationAsset

class BitMapDecorationAsset(
    val bitmap: Bitmap,
    override val id: String
) : DecorationAsset<Bitmap>(id) {
    override fun isEqualsTo(decorationAsset: DecorationAsset<*>): Boolean {
        return id == decorationAsset.id
    }

    override fun value(): Bitmap {
        return bitmap
    }
}
