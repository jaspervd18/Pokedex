package com.example.pokedex.screens.pokemons

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.pokedex.R
import com.example.pokedex.database.favorites.DatabaseFavorite
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import com.example.pokedex.network.PokemonApi
import com.example.pokedex.network.Pokemon
import kotlinx.coroutines.launch
import timber.log.Timber

enum class PokemonApiStatus { LOADING, ERROR, DONE }
class PokemonViewModel(val database: FavoriteDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var counter: Int = 1

    private val _saveEvent = MutableLiveData<Boolean>()
    val saveEvent: LiveData<Boolean>
        get() = _saveEvent

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus>
        get() = _status

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    init {
        _saveEvent.value = false
        getPokemonFromApi(counter)
    }

    val displayHeight = Transformations.map(pokemon) {
        application.applicationContext.getString(R.string.display_height, pokemon.value?.height)
    }

    val displayWeight = Transformations.map(pokemon) {
        application.applicationContext.getString(R.string.display_weight, pokemon.value?.weight)
    }

    val displayPokemonNr = Transformations.map(pokemon) {
        application.applicationContext.getString(
            R.string.display_number, pokemon.value?.id.toString().padStart(3, '0')
        )
    }

    val displayPokemonTypes = Transformations.map(pokemon) {
        if (pokemon.value?.types?.size == 1) {
            application.applicationContext.getString(
                R.string.display_types, pokemon.value?.types?.get(0)!!.type.name
            )
        } else {
            application.applicationContext.getString(
                R.string.display_types,
                pokemon.value?.types?.get(0)!!.type.name + ", " + pokemon.value?.types?.get(1)!!.type.name
            )
        }
    }

    val prevButtonVisible = Transformations.map(pokemon) {
        it?.id != 1
    }

    val nextButtonVisible = Transformations.map(pokemon) {
        it?.id != 905
    }

    fun nextPokemon() {
        if (counter.plus(1) <= 905) counter = counter.plus(1)
        getPokemonFromApi(counter)
    }

    fun previousPokemon() {
        if (counter.minus(1) >= 1) counter = counter.minus(1)
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
            databaseFavorite.pokemonNr = pokemon.value?.id!!
            databaseFavorite.pokemonImgUrl =
                pokemon.value?.sprites?.other?.officialArtwork?.frontDefault.toString()
            saveFavoriteToDatabase(databaseFavorite)
        }
    }

    //suspend methods
    private suspend fun saveFavoriteToDatabase(newDatabaseFavorite: DatabaseFavorite) {
        database.run { insert(newDatabaseFavorite) }
    }

    private fun getPokemonFromApi(id: Int) {

        viewModelScope.launch {
            try {
                _status.value = PokemonApiStatus.LOADING
                val pokemon = PokemonApi.retrofitService.getPokemonAsync(id).await()
                _status.value = PokemonApiStatus.DONE
                _pokemon.value = pokemon
            } catch (e: Exception) {
                _status.value = PokemonApiStatus.ERROR

            }
        }
    }


}