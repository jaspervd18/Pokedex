package com.example.pokedex.network

import com.example.pokedex.database.favorites.DatabaseFavorite
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Gets Pokemon information from the Pokemon API Retrofit service and updates the
 * [Pokemon]. The Retrofit service returns a coroutine
 * Deferred, which we await to get the result of the transaction.
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

/**
 * Types can either contain one or two slots, hence the List
 */
data class Types(
    val slot: Int,
    val type: Type
)

/**
 * The actual name of the type, can be the first or second type
 * The url is not used throughout the application
 */
data class Type(
    val name: String,
    val url: String
)

/**
 * Gets Sprites information from the Pokemon.
 * Multiple sprites exist, here only other sprites are asked.
 */
data class Sprites(
    val other: Others
)

/**
 *  The other sprites contains the official artwork from the Pokemon.
 *  This artwork will be used throughout the whole app.
 */
data class Others(
    // Used to map official-artwork from the JSON to officialArtwork in our class
    @Json(name = "official-artwork") val officialArtwork: OfficialArtwork
)

/**
 * Official Artwork contains the String with the image URL
 */
data class OfficialArtwork(
    // Used to map front-default from the JSON to frontDefault in our class
    @Json(name = "front_default") val frontDefault: String
)

/**
 * Convert network result into Database Favorite Pokemon
 * Returns a Database Favorite
 **/
fun Pokemon.asDatabaseModel(): DatabaseFavorite {
    return DatabaseFavorite(
        pokemonNr = id,
        pokemonName = name,
        pokemonImgUrl = sprites.other.officialArtwork.frontDefault
    )
}
