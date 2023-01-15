package com.example.pokedex.screens.pokemons

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.pokedex.database.favorites.FavoriteDatabase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonCounterTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var context: Context
    private lateinit var pokemonViewModel: PokemonViewModel

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        val dataSource = FavoriteDatabase.getInstance(context).favoriteDatabaseDao
        pokemonViewModel = PokemonViewModel(dataSource, context as Application)
    }

    @Test
    fun onInitializePokemonNr_shouldBe_firstPokemon() {
        assert(pokemonViewModel.counter == 1)
    }

    @Test
    fun nextPokemonNr_shouldBe_secondPokemon() {
        pokemonViewModel.nextPokemon()
        assert(pokemonViewModel.counter == 2)
    }

    @Test
    fun previousPokemonNr_shouldStillBe_firstPokemon() {
        pokemonViewModel.previousPokemon()
        assert(pokemonViewModel.counter == 1)
    }

}