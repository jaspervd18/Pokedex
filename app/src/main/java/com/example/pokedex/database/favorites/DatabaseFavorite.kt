package com.example.pokedex.database.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedex.domain.Pokemon

/**
* Database entity DatabaseFavorite
* This represents a Favorite Pokemon in the database
**/
@Entity(tableName = "favorite_pokemons_table")
data class DatabaseFavorite(
    // The pokemon Nr retrieved from the API is used as the primary key (unique from 0-905)
    @PrimaryKey @ColumnInfo(name = "pokemon_number") var pokemonNr: Int = 0,

    @ColumnInfo(name = "pokemon_name") var pokemonName: String = "",

    @ColumnInfo(name = "pokemon_img_url") var pokemonImgUrl: String = ""
)

/**
 * Convert DatabaseFavorites to a List of Pokemon
 **/
fun List<DatabaseFavorite>.asDomainModel(): List<Pokemon> {
    return map {
        Pokemon(
            pokemonNr = it.pokemonNr,
            pokemonName = it.pokemonName,
            pokemonImgUrl = it.pokemonImgUrl,
        )
    }
}
