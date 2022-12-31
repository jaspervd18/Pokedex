package com.example.pokedex.network

import androidx.lifecycle.LiveData
import com.example.pokedex.database.favorites.DatabaseFavorite
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Gets Pokemon information from the Pokemon API Retrofit service and updates the
 * [Pokemon] and [PokemonkApiStatus] [LiveData]. The Retrofit service returns a coroutine
 * Deferred, which we await to get the result of the transaction.
 * @param filter the [MarsApiFilter] that is sent as part of the web server request
 */
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
