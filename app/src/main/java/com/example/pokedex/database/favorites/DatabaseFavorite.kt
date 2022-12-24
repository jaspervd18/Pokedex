package com.example.pokedex.database.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_pokemons_table")
data class DatabaseFavorite(
    @PrimaryKey
    @ColumnInfo(name = "pokemon_number")
    var pokemonNr: Int = 0,

    @ColumnInfo(name = "pokemon_name")
    var pokemonName: String = "",

    @ColumnInfo(name = "pokemon_img_url")
    var pokemonImgUrl: String = ""
)
