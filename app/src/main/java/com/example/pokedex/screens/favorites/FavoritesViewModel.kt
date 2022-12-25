package com.example.pokedex.screens.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import com.example.pokedex.repository.FavoritePokemonRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(val database: FavoriteDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private val favoritePokemonRepository = FavoritePokemonRepository(database)

    private suspend fun clear() {
        database.clear()
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
            favoritePokemonRepository.refreshFavoritePokemon()
        }
    }

    val favorites = favoritePokemonRepository.favoritePokemons

    val clearButtonVisible = Transformations.map(favorites) {
        it?.isNotEmpty()
    }

}