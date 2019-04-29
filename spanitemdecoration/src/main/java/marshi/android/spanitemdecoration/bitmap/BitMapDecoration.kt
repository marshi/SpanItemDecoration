package marshi.android.spanitemdecoration.bitmap

import android.graphics.Bitmap
import marshi.android.spanitemdecoration.Decoration

class BitMapDecoration(
    private val bitmap: Bitmap,
    override val id: Long
) : Decoration<Bitmap>(id) {
    override fun isEqualsTo(decoration: Decoration<Bitmap>): Boolean {
        return id == decoration.id
    }

    override fun value(): Bitmap {
        return bitmap
    }
}
