package com.example.pokedex.screens.pokemons

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.database.favorites.FavoriteDatabaseDao

/**
 * Simple ViewModel factory that provides the database source and application context to the ViewModel.
 */
class PokemonViewModelFactory(
    private val dataSource: FavoriteDatabaseDao, private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            return PokemonViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
