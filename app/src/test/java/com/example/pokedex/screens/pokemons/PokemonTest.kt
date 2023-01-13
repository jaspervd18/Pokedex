package com.example.pokedex.screens.pokemons

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pokedex.database.favorites.FavoriteDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class PokemonTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokemonViewModel : PokemonViewModel

    @Before
    fun initializeViewModel() {
        val appContext = requireNotNull(PokemonFragment().activity).application
        val dataSource = FavoriteDatabase.getInstance(appContext).favoriteDatabaseDao

        pokemonViewModel = PokemonViewModel(dataSource, appContext)
    }

    @Test
    fun onInitializePokemonNr_shouldBe_firstPokemon() {
        assert(pokemonViewModel.counter == 1)
    }

    @Test
    fun nextPokemon_shouldBe_secondPokemon() {
        pokemonViewModel.nextPokemon()
        assert(pokemonViewModel.counter == 2)
    }

}