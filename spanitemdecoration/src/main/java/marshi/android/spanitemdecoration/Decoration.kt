package marshi.android.spanitemdecoration

abstract class Decoration<T>(open val id: Long) {
    abstract fun isEqualsTo(decoration: Decoration<T>): Boolean
    abstract fun value(): T
}
