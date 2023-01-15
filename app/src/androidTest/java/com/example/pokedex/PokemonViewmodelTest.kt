package com.example.pokedex

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokedex.database.favorites.FavoriteDatabase
import com.example.pokedex.screens.pokemons.PokemonViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonViewmodelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokemonViewModel: PokemonViewModel

    @Before
    fun initializeViewModel() {
        val application = Mockito.mock(Application::class.java)
        val dataSource = FavoriteDatabase.getInstance(application).favoriteDatabaseDao

        pokemonViewModel = PokemonViewModel(dataSource, application)
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