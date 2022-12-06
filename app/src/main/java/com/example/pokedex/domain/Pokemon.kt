package com.example.pokedex.domain

data class Pokemon(
    var pokemonId: Long = 0L,

    var pokemonName: String = "",

    var pokemonType: String = "",

    var imageUrl: String = "",
)