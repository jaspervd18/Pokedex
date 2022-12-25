package com.example.pokedex.database.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedex.domain.Pokemon

@Entity(tableName = "favorite_pokemons_table")
data class DatabaseFavorite(
    @PrimaryKey @ColumnInfo(name = "pokemon_number") var pokemonNr: Int = 0,

    @ColumnInfo(name = "pokemon_name") var pokemonName: String = "",

    @ColumnInfo(name = "pokemon_img_url") var pokemonImgUrl: String = ""
)

fun List<DatabaseFavorite>.asDomainModel(): List<Pokemon> {
    return map {
        Pokemon(
            pokemonNr = it.pokemonNr,
            pokemonName = it.pokemonName,
            pokemonImgUrl = it.pokemonImgUrl,
        )
    }
}
