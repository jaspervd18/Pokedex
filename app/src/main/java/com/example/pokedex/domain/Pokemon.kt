package com.example.pokedex.domain

data class Pokemon(
    var pokemonId: Long = 0L,

    var pokemonName: String = "",

    var pokemonWeight: Double = 0.0,

    var pokemonHeight: Double = 0.0,

    var pokemonType: String = "",

    var imageUrl: String = "",
)