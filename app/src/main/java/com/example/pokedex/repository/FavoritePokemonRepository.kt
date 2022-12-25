package com.example.pokedex.repository

import androidx.lifecycle.LiveData
import com.example.pokedex.database.favorites.DatabaseFavorite
import com.example.pokedex.database.favorites.FavoriteDatabase
import com.example.pokedex.network.Pokemon
import com.example.pokedex.network.PokemonApi
import com.example.pokedex.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritePokemonRepository(private val database: FavoriteDatabase) {

    suspend fun refreshFavoritePokemon() {
        withContext(Dispatchers.IO) {
            val favoritePokemons: List<DatabaseFavorite>? =
                database.favoriteDatabaseDao.getAllFavorites().value

            val favoritePokemon = PokemonApi.retrofitService.getPokemonAsync(1).await()
            database.favoriteDatabaseDao.insert(favoritePokemon.asDatabaseModel())
        }
    }
}