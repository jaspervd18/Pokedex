package com.example.pokedex.repository

import com.example.pokedex.database.favorites.FavoriteDatabase
import com.example.pokedex.network.PokemonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritePokemonRepository(private val database: FavoriteDatabase) {

//    suspend fun refreshFavoritePokemon() {
//        withContext(Dispatchers.IO) {
//            val favoritePokemon = PokemonApi.retrofitService.getPokemonAsync(1).await()
//        }
//    }
}