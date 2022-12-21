package com.example.pokedex.network

import com.example.pokedex.domain.Pokemon
import com.squareup.moshi.Json

data class PokemonProperty(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)

data class Sprites(
    @field:Json(name = "other") val otherSprites: List<OtherSprites>
)

data class OtherSprites(
    @field:Json(name = "official-artwork") val officialArtwork: OfficalArtwork
)

data class OfficalArtwork(
    @Json(name="front_default") val frontDefault: String
)