package se.grit.erik.cveye

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import se.grit.erik.cveye.databinding.ItemCveBinding

class CveAdapter(
    private val items: List<CveItem>,
    private val onItemClick: (CveItem) -> Unit
) : RecyclerView.Adapter<CveAdapter.CveViewHolder>() {

    inner class CveViewHolder(private val binding: ItemCveBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CveItem) {
            binding.tvCveId.text = item.id
            binding.tvPublished.text = "Published: ${item.published}"
            binding.tvDescription.text = item.description

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CveViewHolder {
        val binding = ItemCveBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CveViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}