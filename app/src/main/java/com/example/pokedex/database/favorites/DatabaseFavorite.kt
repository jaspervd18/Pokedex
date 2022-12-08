package com.example.pokedex.database.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_pokemons_table")
data class DatabaseFavorite(
    @PrimaryKey(autoGenerate = true)
    var pokemonId: Long = 0L,

    @ColumnInfo(name = "joke_setup")
    var pokemonName: String = "",
)
