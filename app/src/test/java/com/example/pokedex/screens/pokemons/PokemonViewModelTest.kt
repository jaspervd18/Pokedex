package com.example.pokedex.screens.pokemons

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokedex.database.favorites.FavoriteDatabase
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class PokemonViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

//    @Test
//    fun first_pokemon_shown_is_bulbasaur() {
//        val appContext = requireNotNull(pokemon.activity).application
//        val dataSource = FavoriteDatabase.getInstance(appContext).favoriteDatabaseDao
//
//        val pokemonViewModel = PokemonViewModel()
//
//        assertThat(pokemonViewModel.counter)
//    }
}