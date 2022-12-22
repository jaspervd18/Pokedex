package com.example.pokedex.screens.pokemons

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.database.favorites.DatabaseFavorite
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import com.example.pokedex.network.PokemonApi
import com.example.pokedex.network.Pokemon
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel(val database: FavoriteDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var counter: Int = 1

    private val _saveEvent = MutableLiveData<Boolean>()
    val saveEvent: LiveData<Boolean>
        get() = _saveEvent

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    init {
        _saveEvent.value = false
        getPokemonFromApi(counter)
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun nextPokemon() {
        counter = counter.plus(1)
//        _pokemonNr.value = counter.toString().padStart(3, '0')
        getPokemonFromApi(counter)

    }

    fun previousPokemon() {
        counter = counter.minus(1)
//        _pokemonNr.value = counter.toString().padStart(3, '0')
        getPokemonFromApi(counter)
    }

    fun saveFavoriteClick() {
        _saveEvent.value = true
    }

    fun saveEventDone() {
        _saveEvent.value = false
    }

    fun favoritePokemon() {
        viewModelScope.launch {
            val databaseFavorite = DatabaseFavorite()
            databaseFavorite.pokemonName = pokemon.value?.name.toString()
            databaseFavorite.pokemonNr = pokemon.value?.id.toString()
            databaseFavorite.pokemonImgUrl =
                pokemon.value?.sprites?.other?.officialArtwork?.frontDefault.toString()
            saveFavoriteToDatabase(databaseFavorite)
        }
    }

    //suspend methods
    suspend fun saveFavoriteToDatabase(newDatabaseFavorite: DatabaseFavorite) {
        database.insert(newDatabaseFavorite)
    }

    private fun getPokemonFromApi(id: Int) {

        viewModelScope.launch {
            try {
                var pokemon = PokemonApi.retrofitService.getPokemon(id)
                _pokemon.value = pokemon
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }


}