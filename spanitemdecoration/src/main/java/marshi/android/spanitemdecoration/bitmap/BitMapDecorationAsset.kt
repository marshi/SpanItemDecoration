package marshi.android.spanitemdecoration.bitmap

import android.graphics.Bitmap
import marshi.android.spanitemdecoration.DecorationAsset

class BitMapDecorationAsset(
    private val bitmap: Bitmap,
    override val id: Long
) : DecorationAsset<Bitmap>(id) {
    override fun isEqualsTo(decorationAsset: DecorationAsset<Bitmap>): Boolean {
        return id == decorationAsset.id
    }

    override fun value(): Bitmap {
        return bitmap
    }
}
