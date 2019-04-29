package marshi.android.spanitemdecoration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import marshi.android.spanitemdecoration.databinding.ActivityMainBinding
import marshi.android.spanitemdecoration.databinding.ItemBinding
import marshi.android.spanitemdecoration.string.TextSpanItemDecoration

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val vhAdapter = VhAdapter()
        binding.recyclerview.adapter = vhAdapter
        (1..100).forEach {
            vhAdapter.add("$it")
        }
        binding.recyclerview.addItemDecoration(
            TextSpanItemDecoration(
                this,
                vhAdapter,
                resources.getDimensionPixelSize(R.dimen.session_bottom_sheet_left_time_text_size),
                resources.getDimensionPixelSize(R.dimen.session_bottom_sheet_left_time_text_left),
                resources.getDimensionPixelSize(R.dimen.session_bottom_sheet_left_time_text_padding_top),
                resources.getDimensionPixelSize(R.dimen.session_bottom_sheet_left_time_text_padding_bottom)
            )
        )
//        binding.recyclerview.addItemDecoration(
//            BitmapSpanItemDecoration(
//                this,
//                vhAdapter
//            )
//        )
    }
}

class VH(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

class VhAdapter() : RecyclerView.Adapter<VH>() {

    private val list: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding: ItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item, parent, false)
        return VH(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.text.text = list[position]
    }

    fun add(text: String) {
        list.add(text)
        notifyDataSetChanged()
    }
}
