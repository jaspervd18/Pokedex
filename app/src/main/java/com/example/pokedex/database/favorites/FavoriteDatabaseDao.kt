package com.example.pokedex.database.favorites

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * Contains functions to insert and get favorite pokemon
 **/
@Dao
interface FavoriteDatabaseDao {

    // Insert a new favorite, if favorite already exists, it gets replaced
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: DatabaseFavorite)

    // Update an existing favorite pokemon (rarely used because of REPLACE in Insert)
    @Update
    suspend fun update(favorite: DatabaseFavorite)

    // Get 1 pokemon with certain pokemon Number
    @Query("SELECT * from favorite_pokemons_table WHERE pokemon_number = :key")
    suspend fun get(key: Int): DatabaseFavorite

    // Delete all favorite pokemon
    @Query("DELETE FROM favorite_pokemons_table")
    suspend fun clear()

    // Get all Favorite Pokemon in order of Pokemon Nr
    @Query("SELECT * FROM favorite_pokemons_table ORDER BY pokemon_number")
    fun getAllFavorites(): LiveData<List<DatabaseFavorite>>
}
