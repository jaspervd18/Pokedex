package com.example.pokedex.screens.bindingUtils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.pokedex.database.favorites.DatabaseFavorite

@BindingAdapter("pokemonNameString")
fun TextView.setPokemonNameString(item: DatabaseFavorite?){
    item?.let {
        text = item.pokemonName
    }
}