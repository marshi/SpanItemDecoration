package marshi.android.spanitemdecoration

abstract class DecorationAsset<T>(open val id: Long) {
    abstract fun isEqualsTo(decorationAsset: DecorationAsset<T>): Boolean
    abstract fun value(): T
}
