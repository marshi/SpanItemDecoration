package marshi.android.spanitemdecoration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import marshi.android.spanitemdecoration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.stringSample.setOnClickListener {
            startActivity(StringSampleActivity.createIntent(this))
        }
        binding.bitmapSample.setOnClickListener {
            startActivity(BitmapSampleActivity.createIntent(this))
        }
    }
}
