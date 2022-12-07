package com.example.pokedex.screens.pokemons

import android.util.Log
import androidx.lifecycle.ViewModel

class PokemonViewModel : ViewModel() {

    //Current pokemon name
    var name = ""

    private lateinit var pokemonList: MutableList<String>

    init {
        Log.i("PokemonViewModel", "PokemonViewModel created!")
        resetList()
        nextPokemon()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("PokemonViewModel", "PokemonViewModel destroyed!")
    }

    private fun resetList() {
        pokemonList = mutableListOf(
            "bulbasaur",
            "ivysaur",
            "venusaur",
            "squirtle",
            "wartortle",
            "blastoise",
            "charmander",
            "charmeleon",
            "charizard",
            "pikachu",
        )
        pokemonList.shuffle()
    }

    fun nextPokemon() {
        name = pokemonList.random()
    }

    fun previousPokemon() {
        name = pokemonList.random()
    }


}