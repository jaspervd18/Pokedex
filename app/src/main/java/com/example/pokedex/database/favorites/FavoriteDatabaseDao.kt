package com.example.pokedex.database.favorites

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: DatabaseFavorite)

    @Update
    suspend fun update(favorite: DatabaseFavorite)

    @Query("SELECT * from favorite_pokemons_table WHERE pokemon_number = :key")
    suspend fun get(key: Int): DatabaseFavorite

    @Query("DELETE FROM favorite_pokemons_table")
    suspend fun clear()

    @Query("SELECT * FROM favorite_pokemons_table ORDER BY pokemon_number")
    fun getAllFavorites(): LiveData<List<DatabaseFavorite>>

    // get the pokemon with the highest ID (last pokemon added)
    @Query("SELECT * FROM favorite_pokemons_table ORDER BY pokemon_number DESC LIMIT 1")
    suspend fun getLastPokemon(): DatabaseFavorite?
}
