package com.example.pokedex.screens.pokemons

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PokemonViewModel : ViewModel() {

    // Current pokemon name
    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name

    // Current pokemon Id
    private val _id = MutableLiveData<Int>()
    val id : LiveData<Int>
        get() = _id

    private lateinit var pokemonList: MutableList<String>

    init {
        resetList()
        nextPokemon()
        _id.value = 1
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
        _id.value = (id.value)?.plus(1)
    }

    fun previousPokemon() {
        _name.value = pokemonList.random()
        _id.value = (id.value)?.minus(1)
    }


}