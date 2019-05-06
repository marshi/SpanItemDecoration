# SpanItemDecoration

SpanItemDecoration extends RecyclerView.ItemDecoration.

It shows RecyclerView decoration astride multiple items.

Inspired by Droidkaigi 2019 official app.

# Samples 
## String Decoration sample
<img src="https://user-images.githubusercontent.com/1423942/57195219-154cc200-6f8b-11e9-859e-7418a30b4538.gif" width="200" />

## Bitmap Decoration sample
<img src="https://user-images.githubusercontent.com/1423942/57195261-678de300-6f8b-11e9-9a29-c4d392cac457.gif" width="200" />

# How to use
## Gradle
```
repositories {
    maven {
        url 'https://dl.bintray.com/marshi/maven'
    }
}

implementation 'marshi.android:spaniemdecoration:1.0'
```

## Bitmap Decoration Sample Code

Extends BitmapSpanItemDecoration and override `asset(position: Int)` function.

The function return DecorationAsset which define asset resource and asset id according to position.

If id is same value between consecutive position, latter asset is not shown.

In ConcreteBitmapSpamItemDecoration class case, when position value is 0 or 1, same id is set.

Therefore a bitmap is shown when position value is 0 and is not shown when the value is 1.

```
class ConcreteBitmapSpamItemDecoration(
    context: Context,
    private val groupAdapter: RecyclerView.Adapter<*>,
    @DimenRes leftSpace: Int,
    @DimenRes paddingTop: Int,
    @DimenRes paddingBottom: Int
) : BitmapSpanItemDecoration(
    context,
    leftSpace,
    paddingTop,
    paddingBottom
) {

    private val bitmap = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_launcher
    )

    private val bitmapRound = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_launcher_round
    )

    override fun asset(position: Int): DecorationAsset<Bitmap>? {
        if (position < 0 || position >= groupAdapter.itemCount) {
            return null
        }

        return when (position / 2 % 2) {
            0 -> DecorationAsset(bitmap, R.drawable.ic_launcher.toString())
            1 -> DecorationAsset(bitmapRound, R.drawable.ic_launcher_round.toString())
            else -> DecorationAsset(bitmap, R.drawable.ic_launcher_round.toString())
        }
    }
}
```
