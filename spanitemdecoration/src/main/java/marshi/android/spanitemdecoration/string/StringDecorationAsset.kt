package marshi.android.spanitemdecoration.string

import marshi.android.spanitemdecoration.DecorationAsset

class StringDecorationAsset(
    val string: String,
    override val id: String
) : DecorationAsset<String>(id) {
    override fun isEqualsTo(decorationAsset: DecorationAsset<*>): Boolean {
        return id == decorationAsset.id
    }

    override fun value(): String {
        return string
    }
}
