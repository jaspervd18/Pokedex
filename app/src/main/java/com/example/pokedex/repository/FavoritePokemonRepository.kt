package com.example.pokedex.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.pokedex.database.favorites.DatabaseFavorite
import com.example.pokedex.database.favorites.FavoriteDatabase
import com.example.pokedex.database.favorites.asDomainModel
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.network.PokemonApi
import com.example.pokedex.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritePokemonRepository(private val database: FavoriteDatabase) {

    val favoritePokemons: LiveData<List<Pokemon>> =
        Transformations.map(database.favoriteDatabaseDao.getAllFavorites()) {
            it.asDomainModel()
        }

    suspend fun refreshFavoritePokemon() {
        withContext(Dispatchers.IO) {
            for (favorite in favoritePokemons.value!!) {
                val favoritePokemon =
                    PokemonApi.retrofitService.getPokemonAsync(favorite.pokemonNr).await()
                database.favoriteDatabaseDao.insert(favoritePokemon.asDatabaseModel())
            }
        }
    }
}
