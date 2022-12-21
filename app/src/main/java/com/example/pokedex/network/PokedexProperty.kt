package com.example.pokedex.network

import com.squareup.moshi.Json

data class PokedexProperty(
//    val id: Int,
    @Json(name="results") val pokedex: List<Pokemon>
//    val height: Int,
//    val weight: Int,
//    val sprites: Sprites
)

data class Pokemon(
    val name: String,
//    val url: String
)

//data class Sprites(
//    val other: List<Other>
//)
//
//data class Other(
//    @field:Json(name = "official-artwork") val officialArtwork: OfficalArtwork
//)
//
//data class OfficalArtwork(
//    @Json(name="front_default") val frontDefault: String
//)