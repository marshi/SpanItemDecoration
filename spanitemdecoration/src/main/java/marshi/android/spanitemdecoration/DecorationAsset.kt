package marshi.android.spanitemdecoration

class DecorationAsset<T>(
    val value: T,
    private val id: String
) {
    fun isEqualsTo(decorationAsset: DecorationAsset<*>): Boolean {
        return id == decorationAsset.id
    }
}
