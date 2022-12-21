package com.example.pokedex.screens.pokemons

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import com.example.pokedex.network.PokedexProperty
import com.example.pokedex.network.PokemonApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel(val database: FavoriteDatabaseDao, application: Application) :
    AndroidViewModel(application) {

//    private var counter = 0


    // Current pokemon name
//    private val _name = MutableLiveData<String>()
//    val name: LiveData<String>
//        get() = _name
//
//    // Current pokemon Id
//    private val _pokemonNr = MutableLiveData<String>()
//    val pokemonNr: LiveData<String>
//        get() = _pokemonNr
//
//    private val _saveEvent = MutableLiveData<Boolean>()
//    val saveEvent: LiveData<Boolean>
//        get() = _saveEvent
//
//    private lateinit var pokemonList: MutableList<String>


//    override fun onCleared() {
//        super.onCleared()
//    }
//
//    private fun resetList() {
//        pokemonList = mutableListOf(
//            "Bulbasaur",
//            "Ivysaur",
//            "Venusaur",
//            "Squirtle",
//            "Wartortle",
//            "Blastoise",
//            "Charmander",
//            "Charmeleon",
//            "Charizard",
//            "Pikachu",
//        )
//        pokemonList.shuffle()
//    }
//
//    fun nextPokemon() {
//        _name.value = pokemonList.random()
//        counter = counter.plus(1)
//        _pokemonNr.value = counter.toString().padStart(3, '0')
//    }
//
//    fun previousPokemon() {
//        _name.value = pokemonList.random()
//        counter = counter.minus(1)
//        _pokemonNr.value = counter.toString().padStart(3, '0')
//    }
//
//    fun saveFavoriteClick() {
//        _saveEvent.value = true
//    }
//
//    fun saveEventDone() {
//        _saveEvent.value = false
//    }
//
//    fun favoritePokemon(newFavorite: String) {
//        viewModelScope.launch {
//            val databaseFavorite = DatabaseFavorite()
//            databaseFavorite.pokemonName = newFavorite
//            saveFavoriteToDatabase(databaseFavorite)
//        }
//    }
//
//    //suspend methods
//    suspend fun saveFavoriteToDatabase(newDatabaseFavorite: DatabaseFavorite) {
//        database.insert(newDatabaseFavorite)
//    }

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
//        resetList()
//        nextPokemon()
//        _pokemonNr.value = "001"
//        _saveEvent.value = false
        getPokemonFromApi()
    }


    private fun getPokemonFromApi() {
        viewModelScope.launch {
            try {
                var pokedex = PokemonApi.retrofitService.getPokedex()
                _response.value = "Success: ${pokedex} pokemons retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }


}