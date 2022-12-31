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

/**
 * Class to connect the Database and the network.
 * Contains a LiveData object with Favorite Pokemon.
 * Can refresh and delete favorite pokemon.
 * */
class FavoritePokemonRepository(private val database: FavoriteDatabaseDao) {

    /**
     * A list of favorite pokemon that can be shown on the screen.
     */
    val favoritePokemons: LiveData<List<Pokemon>> =
        Transformations.map(database.getAllFavorites()) {
            it.asDomainModel()
        }

    /**
     * Refresh the favorite pokemon stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the favorite pokemon for use, observe [favoritePokemons].
     */
    suspend fun refreshFavoritePokemon() {
        withContext(Dispatchers.IO) {
            for (favorite in favoritePokemons.value!!) {
                val favoritePokemon =
                    PokemonApi.retrofitService.getPokemonAsync(favorite.pokemonNr).await()
                database.insert(favoritePokemon.asDatabaseModel())
            }
        }
    }

    /**
     * Delete all the favorite pokemon stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     */
    suspend fun deleteFavoritePokemon() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }
}
