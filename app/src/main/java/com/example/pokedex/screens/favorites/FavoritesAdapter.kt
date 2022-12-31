package com.example.pokedex.screens.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.FavoriteListItemBinding
import com.example.pokedex.domain.Pokemon

class FavoritesAdapter(private val clickListener: FavoritesListener) :
    ListAdapter<Pokemon, ViewHolder>(FavoriteDiffCallback()) {

    // fill up the item you need (e.g. set texts and images)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}

class ViewHolder(val binding: FavoriteListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: FavoritesListener, item: Pokemon) {
        binding.favorite = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    // this way the viewHolder knows how to inflate.
    // better than having this in the adapter.
    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FavoriteListItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}

class FavoriteDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.pokemonNr == newItem.pokemonNr
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}

class FavoritesListener(val clickListener: (pokemonName: String) -> Unit) {
    fun onClick(pokemon: Pokemon) = clickListener(pokemon.pokemonName)
}
