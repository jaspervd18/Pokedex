package com.example.pokedex.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import com.example.pokedex.database.favorites.asDomainModel
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.network.PokemonApi
import com.example.pokedex.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritePokemonRepository(private val database: FavoriteDatabaseDao) {

    val favoritePokemons: LiveData<List<Pokemon>> =
        Transformations.map(database.getAllFavorites()) {
            it.asDomainModel()
        }

    suspend fun refreshFavoritePokemon() {
        withContext(Dispatchers.IO) {
            for (favorite in favoritePokemons.value!!) {
                val favoritePokemon =
                    PokemonApi.retrofitService.getPokemonAsync(favorite.pokemonNr).await()
                database.insert(favoritePokemon.asDatabaseModel())
            }
        }
    }

    suspend fun deleteFavoritePokemon() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }
}
