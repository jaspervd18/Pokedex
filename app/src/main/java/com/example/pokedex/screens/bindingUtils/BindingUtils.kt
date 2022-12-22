package com.example.pokedex.screens.bindingUtils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.database.favorites.DatabaseFavorite
import com.example.pokedex.screens.pokemons.PokemonApiStatus

@BindingAdapter("pokemonNameString")
fun TextView.setPokemonNameString(item: DatabaseFavorite?) {
    item?.let {
        text = item.pokemonName
    }
}

@BindingAdapter("pokemonNrString")
fun TextView.setPokemonNrString(item: DatabaseFavorite?) {
    item?.let {
        text = item.pokemonNr.padStart(3, '0')
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context).load(imgUri).into(imgView)
    }
}

@BindingAdapter("pokemonApiStatus")
fun bindStatus(statusImageView: ImageView, status: PokemonApiStatus?) {
    when (status) {
        PokemonApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PokemonApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.connection_error)
        }
        PokemonApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.connection_error)
        }
    }
}