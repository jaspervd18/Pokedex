package com.example.pokedex.domain

/**
 * Model class: this is how the Pokemon is known in the database and repository
 * (without weight and height)
 **/
data class Pokemon(
    val pokemonNr: Int = 0,
    val pokemonName: String = "",
    val pokemonImgUrl: String = "",
)
