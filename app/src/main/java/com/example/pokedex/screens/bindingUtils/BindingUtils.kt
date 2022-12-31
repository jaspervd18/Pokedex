package com.example.pokedex.screens.bindingUtils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.pokedex.R
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.screens.pokemons.PokemonApiStatus

@BindingAdapter("pokemonNameString")
fun TextView.setPokemonNameString(item: Pokemon) {
    item?.let {
        text = item.pokemonName.uppercase()
    }
}

@BindingAdapter("pokemonNrString")
fun TextView.setPokemonNrString(item: Pokemon) {
    item?.let {
        text = item.pokemonNr.toString().padStart(3, '0')
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
