package com.example.pokedex.screens.pokemons

import android.app.Application
import androidx.lifecycle.*
import com.example.pokedex.R
import com.example.pokedex.database.favorites.DatabaseFavorite
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import com.example.pokedex.network.Pokemon
import com.example.pokedex.network.PokemonApi
import kotlinx.coroutines.launch

enum class PokemonApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [PokemonFragment].
 */
class PokemonViewModel(val database: FavoriteDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    // Counter for keeping the current pokemon
    var counter: Int

    // The internal MutableLiveData that stores the status of the Save event
    private val _saveEvent = MutableLiveData<Boolean>()

    // The external immutable LiveData for the status of the Save event
    val saveEvent: LiveData<Boolean>
        get() = _saveEvent

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<PokemonApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<PokemonApiStatus>
        get() = _status

    // Internal MutableLiveData for updating Pokemon
    private val _pokemon = MutableLiveData<Pokemon>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    /**
     * Set saveEvent to false
     * Call getPokemonFromApi(counter) on init so we can display the first pokemon immediately.
     */
    init {
        _saveEvent.value = false
        counter = 1;
        getPokemonFromApi(counter)
    }

    /**
     * Display Height of Pokemon in certain format.
     */
    val displayHeight = Transformations.map(pokemon) {
        application.applicationContext.getString(R.string.display_height, pokemon.value?.height)
    }

    /**
     * Display Weight of Pokemon in certain format.
     */
    val displayWeight = Transformations.map(pokemon) {
        application.applicationContext.getString(R.string.display_weight, pokemon.value?.weight)
    }

    /**
     * Display Pokemon Number in certain format.
     */
    val displayPokemonNr = Transformations.map(pokemon) {
        application.applicationContext.getString(
            R.string.display_number, pokemon.value?.id.toString().padStart(3, '0')
        )
    }

    /**
     * Display Pokemon Types in certain format depending on 1 or either 2 types.
     */
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

    /**
     * Disable the previous button on the first pokemon.
     */
    val prevButtonVisible = Transformations.map(pokemon) {
        it?.id != 1
    }

    /**
     * Disable the next button on the last pokemon.
     */
    val nextButtonVisible = Transformations.map(pokemon) {
        it?.id != 905
    }

    /**
     * Increment the counter and ask for the new Pokemon with getPokemonFromApi(counter).
     */
    fun nextPokemon() {
        if (counter.plus(1) <= 905) counter = counter.plus(1)
        getPokemonFromApi(counter)
    }

    /**
     * Decrement the counter and ask for the new Pokemon with getPokemonFromApi(counter).
     */
    fun previousPokemon() {
        if (counter.minus(1) >= 1) counter = counter.minus(1)
        getPokemonFromApi(counter)
    }

    /**
     * Set the save event to true, which initiates the saving process.
     */
    fun saveFavoriteClick() {
        _saveEvent.value = true
    }

    /**
     * Set the save event to false, which marks the end of the saving process.
     */
    fun saveEventDone() {
        _saveEvent.value = false
    }

    /**
     * Launch the viewModelScope en save the current displayed pokemon to the Database.
     * Only the name, id and imageUrl of the pokemon are saved.
     * Call saveFavoriteToDatabase(databaseFavorite) to run the database method
     */
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

    /**
     * Saves the new favorite to the database
     * @param newDatabaseFavorite the [DatabaseFavorite] that is a new favorite
     */
    private suspend fun saveFavoriteToDatabase(newDatabaseFavorite: DatabaseFavorite) {
        database.run { insert(newDatabaseFavorite) }
    }

    /**
     * Gets filtered Mars real estate property information from the Mars API Retrofit service and
     * updates the [Pokemon] and [PokemonApiStatus] [LiveData]. The Retrofit service
     * returns a coroutine Deferred, which we await to get the result of the transaction.
     * @param id the [Integer] that is sent as part of the web server request
     */
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
