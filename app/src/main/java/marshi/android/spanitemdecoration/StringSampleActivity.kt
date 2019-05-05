package marshi.android.spanitemdecoration

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import marshi.android.spanitemdecoration.databinding.ActivityStringSampleBinding

class StringSampleActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context) = Intent(context, StringSampleActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStringSampleBinding = DataBindingUtil.setContentView(this, R.layout.activity_string_sample)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val vhAdapter = Adapter()
        binding.recyclerview.adapter = vhAdapter
        (1..100).forEach {
            vhAdapter.add("sample item $it")
        }
        binding.recyclerview.addItemDecoration(
            ConcreteStringSpanItemDecoration(
                this,
                vhAdapter,
                R.dimen.text_size,
                R.dimen.padding_left,
                R.dimen.padding_top,
                R.dimen.padding_bottom,
                Color.BLUE
            )
        )
    }
}
