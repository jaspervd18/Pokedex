package com.example.pokedex.screens.pokemons

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PokemonViewModel : ViewModel() {

    // Current pokemon name
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    // Current pokemon Id
    private val _pokemonNr = MutableLiveData<String>()
    val pokemonNr: LiveData<String>
        get() = _pokemonNr

    private lateinit var pokemonList: MutableList<String>

    init {
        resetList()
        nextPokemon()
        _pokemonNr.value = "001"
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
        _name.value = pokemonList.random()
        _pokemonNr.value = "002"
    }

    fun previousPokemon() {
        _name.value = pokemonList.random()
        _pokemonNr.value = "000"

    }


}