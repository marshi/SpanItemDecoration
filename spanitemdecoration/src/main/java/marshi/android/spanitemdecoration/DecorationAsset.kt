package marshi.android.spanitemdecoration

abstract class DecorationAsset<T>(open val id: String) {
    abstract fun isEqualsTo(decorationAsset: DecorationAsset<*>): Boolean
    abstract fun value(): T
}
