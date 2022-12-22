package com.example.pokedex.screens.bindingUtils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pokedex.database.favorites.DatabaseFavorite

@BindingAdapter("pokemonNameString")
fun TextView.setPokemonNameString(item: DatabaseFavorite?) {
    item?.let {
        text = item.pokemonName
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context).load(imgUri).into(imgView)
    }
}