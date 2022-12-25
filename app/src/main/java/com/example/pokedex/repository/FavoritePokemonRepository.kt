package com.example.pokedex.repository

import com.example.pokedex.database.favorites.DatabaseFavorite
import com.example.pokedex.database.favorites.FavoriteDatabase
import com.example.pokedex.network.PokemonApi
import com.example.pokedex.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritePokemonRepository(private val database: FavoriteDatabase) {

    suspend fun refreshFavoritePokemon() {
        withContext(Dispatchers.IO) {
            val favoritePokemons: List<DatabaseFavorite>? =
                database.favoriteDatabaseDao.getAllFavorites().value

            if (favoritePokemons != null) {
                for (favorite in favoritePokemons) {
                    val favoritePokemon =
                        PokemonApi.retrofitService.getPokemonAsync(favorite.pokemonNr).await()
                    database.favoriteDatabaseDao.insert(favoritePokemon.asDatabaseModel())
                }
            }
        }
    }
}