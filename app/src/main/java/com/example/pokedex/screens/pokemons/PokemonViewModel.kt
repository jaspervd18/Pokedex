package com.example.pokedex.screens.pokemons

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import com.example.pokedex.network.PokemonApi
import com.example.pokedex.network.Pokemon
import kotlinx.coroutines.launch

class PokemonViewModel(val database: FavoriteDatabaseDao, application: Application) :
    AndroidViewModel(application) {


//    private val _saveEvent = MutableLiveData<Boolean>()
//    val saveEvent: LiveData<Boolean>
//        get() = _saveEvent

//    override fun onCleared() {
//        super.onCleared()
//    }
//
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

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    init {
//        nextPokemon()
//        _saveEvent.value = false
        getPokemonFromApi()
    }


    private fun getPokemonFromApi() {
        viewModelScope.launch {
            try {
                var getPokemonDeferred = PokemonApi.retrofitService.getPokemon()
                var result = getPokemonDeferred.await()
                if (result !== null) {
                    _pokemon.value = result
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }


}