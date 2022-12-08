package com.example.pokedex.database.favorites

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDatabaseDao {

    @Insert
    fun insert(favorite: DatabaseFavorite)

    @Update
    fun update(favorite: DatabaseFavorite)

    @Query("SELECT * from favorite_pokemons_table WHERE pokemonId = :key")
    fun get(key: Long): DatabaseFavorite

//    @Query("DELETE FROM favorite_pokemons_table")
//    fun clear()

    @Query("SELECT * FROM favorite_pokemons_table ORDER BY pokemonId DESC")
    fun getAllFavorites(): LiveData<List<DatabaseFavorite>>

}