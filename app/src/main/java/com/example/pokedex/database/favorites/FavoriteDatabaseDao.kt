package com.example.pokedex.database.favorites

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDatabaseDao {

    @Insert
    suspend fun insert(favorite: DatabaseFavorite)

    @Update
    suspend fun update(favorite: DatabaseFavorite)

    @Query("SELECT * from favorite_pokemons_table WHERE pokemonId = :key")
    suspend fun get(key: Long): DatabaseFavorite

    @Query("DELETE FROM favorite_pokemons_table")
    suspend fun clear()

    @Query("SELECT * FROM favorite_pokemons_table ORDER BY pokemon_number")
    fun getAllFavorites(): LiveData<List<DatabaseFavorite>>

    //get the pokemon with the highest ID (last pokemon added)
    @Query("SELECT * FROM favorite_pokemons_table ORDER BY pokemonId DESC LIMIT 1")
    suspend fun getLastPokemon(): DatabaseFavorite?

}