package com.example.pokedex.screens.pokemons

import android.util.Log
import androidx.lifecycle.ViewModel

class PokemonViewModel : ViewModel() {
    init {
        Log.i("PokemonViewModel", "PokemonViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("PokemonViewModel", "PokemonViewModel destroyed!")
    }
}