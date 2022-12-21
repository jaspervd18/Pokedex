package com.example.pokedex.network

import com.example.pokedex.domain.Pokemon

data class ApiPokemon(
    val body: List<Pokemon>
) {
    //Hardcoded image url to demo Glide
    val squirtleUri =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"
}