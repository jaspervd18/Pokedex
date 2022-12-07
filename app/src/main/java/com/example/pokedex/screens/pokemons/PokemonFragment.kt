package com.example.pokedex.screens.pokemons

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonBinding

class PokemonFragment : Fragment() {

    private lateinit var viewModel: PokemonViewModel

    private lateinit var binding : FragmentPokemonBinding

    //current pokemon name
    private var name = "Bulbasaur"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon, container, false)

        Log.i("PokemonFragment", "Called ViewModelProviders.of!")
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)

        binding.pokemonName.text = name

        binding.nextpokemonButton.setOnClickListener { nextPokemon() }
        binding.previouspokemonButton.setOnClickListener { previousPokemon() }

        return binding.root
    }

    private fun previousPokemon() {
        binding.pokemonName.text = "Charmander"
    }

    private fun nextPokemon() {
        binding.pokemonName.text = "Pikachu"
    }



}