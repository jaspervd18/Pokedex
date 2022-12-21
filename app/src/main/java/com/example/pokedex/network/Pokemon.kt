package com.example.pokedex.network

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
//    val sprites: Sprites
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