package com.example.pokedex.screens.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.database.favorites.DatabaseFavorite
import com.example.pokedex.databinding.FavoriteListItemBinding

class FavoritesAdapter(val clickListener: FavoritesListener) :
    ListAdapter<DatabaseFavorite, ViewHolder>(FavoriteDiffCallback()) {

    //fill up the item you need (e.g. set texts and images)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

}

class ViewHolder(val binding: FavoriteListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: FavoritesListener, item: DatabaseFavorite) {
        binding.pokemonNameTextView.text = item.pokemonName

        binding.favorite = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    //this way the viewHolder knows how to inflate.
    //better than having this in the adapter.
    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FavoriteListItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}


class FavoriteDiffCallback : DiffUtil.ItemCallback<DatabaseFavorite>() {
    override fun areItemsTheSame(oldItem: DatabaseFavorite, newItem: DatabaseFavorite): Boolean {
        return oldItem.pokemonId == newItem.pokemonId
    }

    override fun areContentsTheSame(oldItem: DatabaseFavorite, newItem: DatabaseFavorite): Boolean {
        return oldItem == newItem
        //works perfectly because it's a dataclass.
    }
}

class FavoritesListener(val clickListener: (pokemonID: Long) -> Unit) {
    fun onClick(databaseFavorite: DatabaseFavorite) = clickListener(databaseFavorite.pokemonId)
}