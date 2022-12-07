package com.example.pokedex.screens.pokemons

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PokemonViewModel : ViewModel() {

    //Current pokemon name
    var name = MutableLiveData<String>()

    var id = MutableLiveData<Int>()

    private lateinit var pokemonList: MutableList<String>

    init {
        resetList()
        nextPokemon()
        id.value = 1
    }

    override fun onCleared() {
        super.onCleared()
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
        name.value = pokemonList.random()
        id.value = (id.value)?.plus(1)
    }

    fun previousPokemon() {
        name.value = pokemonList.random()
        id.value = (id.value)?.minus(1)
    }


}