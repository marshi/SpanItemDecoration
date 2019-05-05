package marshi.android.spanitemdecoration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import marshi.android.spanitemdecoration.databinding.ItemBinding

class VH(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

class Adapter() : RecyclerView.Adapter<VH>() {

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