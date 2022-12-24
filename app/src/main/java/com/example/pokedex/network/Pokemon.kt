package com.example.pokedex.network

import com.squareup.moshi.Json

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