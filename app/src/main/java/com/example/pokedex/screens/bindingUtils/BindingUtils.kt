package com.example.pokedex.screens.bindingUtils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.screens.pokemons.PokemonApiStatus

/**
 * Adapter will put the pokemon name in all caps
 */
@BindingAdapter("pokemonNameString")
fun TextView.setPokemonNameString(item: Pokemon) {
    item.let {
        text = item.pokemonName.uppercase()
    }
}

/**
 * Adapter will append the pokemon Nr with zero's so that it always has 3 digits
 */
@BindingAdapter("pokemonNrString")
fun TextView.setPokemonNrString(item: Pokemon) {
    item.let {
        text = item.pokemonNr.toString().padStart(3, '0')
    }
}

/**
 * Adapter will put the pokemon Uri to the image
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context).load(imgUri).into(imgView)
    }
}

/**
 * Adapter will adapt the status to an image
 */
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
