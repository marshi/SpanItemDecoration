package marshi.android.spanitemdecoration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import marshi.android.spanitemdecoration.databinding.ActivityBitmapSampleBinding

class BitmapSampleActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context) = Intent(context, BitmapSampleActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBitmapSampleBinding = DataBindingUtil.setContentView(this, R.layout.activity_bitmap_sample)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val vhAdapter = Adapter()
        binding.recyclerview.adapter = vhAdapter
        (1..100).forEach {
            vhAdapter.add("$it")
        }
        binding.recyclerview.addItemDecoration(
            ConcreteBitmapSpamItemDecoration(
                this,
                vhAdapter,
                R.dimen.padding_left, R.dimen.padding_top, R.dimen.padding_bottom
            )
        )
    }
}
