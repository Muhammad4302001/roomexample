package com.example.favorite.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite.R
import com.example.favorite.database.model.Favorite
import com.example.favorite.databinding.ItemBinding
import kotlinx.coroutines.runBlocking

interface MetanCallback {
    fun add(metan: Metan)
    fun remove(metan: Metan)
}

class ItemAdapter(
    private val items: List<Metan>,
    private val favorites: List<Favorite>,
    private val metanCallback: MetanCallback
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(view, metanCallback)
    }

    private fun isChecked(metan: Metan): Boolean {
        return favorites.find { favorite -> favorite.item_id == metan.id } != null
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.bind(items[position], isChecked(items[position]))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ItemViewHolder(view: View, private val metanCallback: MetanCallback) : RecyclerView.ViewHolder(view) {

    private val binding: ItemBinding = ItemBinding.bind(view)

    fun bind(metan: Metan, isChecked: Boolean) {
        binding.name.text = metan.name
        binding.check.isChecked = isChecked

        binding.check.setOnClickListener {
            if (binding.check.isChecked ) {
                metanCallback.add(metan)
            }
            else {
                metanCallback.remove(metan)
            }
        }
    }
}