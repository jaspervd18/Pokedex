package com.example.pokedex.screens.pokemons

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.database.favorites.DatabaseFavorite
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import kotlinx.coroutines.launch

class PokemonViewModel(val database: FavoriteDatabaseDao, application: Application): AndroidViewModel(application) {

    private var counter = 0

    // Current pokemon name
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    // Current pokemon Id
    private val _pokemonNr = MutableLiveData<String>()
    val pokemonNr: LiveData<String>
        get() = _pokemonNr

    private val _saveEvent = MutableLiveData<Boolean>()
    val saveEvent: LiveData<Boolean>
        get() = _saveEvent

    private lateinit var pokemonList: MutableList<String>

    init {
        resetList()
        nextPokemon()
        _pokemonNr.value = "001"
        _saveEvent.value = false
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
        counter = counter.plus(1)
        _pokemonNr.value = counter.toString().padStart(3, '0')
    }

    fun previousPokemon() {
        _name.value = pokemonList.random()
        counter = counter.minus(1)
        _pokemonNr.value = counter.toString().padStart(3, '0')
    }

    fun saveFavoriteClick(){
        _saveEvent.value = true
    }

    fun saveEventDone(){
        _saveEvent.value = false
    }

    fun favoritePokemon(newFavorite : String) {
        viewModelScope.launch{
            val databaseFavorite = DatabaseFavorite()
            databaseFavorite.pokemonName = newFavorite
            saveFavoriteToDatabase(databaseFavorite)
        }
    }

    //suspend methods
    suspend fun saveFavoriteToDatabase(newDatabaseFavorite: DatabaseFavorite){
        database.insert(newDatabaseFavorite)
    }


}