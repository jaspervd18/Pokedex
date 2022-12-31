package com.example.pokedex.screens.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import com.example.pokedex.repository.FavoritePokemonRepository
import com.example.pokedex.screens.pokemons.PokemonFragment
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [PokemonFragment].
 */
class FavoritesViewModel(val database: FavoriteDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private val favoritePokemonRepository = FavoritePokemonRepository(database)

    /**
     * Delete all pokemon from the favorite repository
     */
    fun onClear() {
        viewModelScope.launch {
            favoritePokemonRepository.deleteFavoritePokemon()
        }
    }

    /**
     * Get all favorite pokemons from repository
     */
    val favorites = favoritePokemonRepository.favoritePokemons

    /**
     * Disable clear button if repository is empty
     */
    val clearButtonVisible = Transformations.map(favorites) {
        it?.isNotEmpty()
    }
}
