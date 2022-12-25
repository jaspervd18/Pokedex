package com.example.pokedex.network

import com.example.pokedex.database.favorites.DatabaseFavorite
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<Types>
)

data class Sprites(
    val other: Others
)

data class Types(
    val slot: Int,
    val type: Type
)

data class Others(
    @Json(name = "official-artwork") val officialArtwork: OfficialArtwork
)

data class Type(
    val name: String,
    val url: String
)

data class OfficialArtwork(
    @Json(name = "front_default") val frontDefault: String
)

fun Pokemon.asDatabaseModel(): DatabaseFavorite {
    return DatabaseFavorite(
        pokemonNr = id,
        pokemonName = name,
        pokemonImgUrl = sprites.other.officialArtwork.frontDefault
    )
}