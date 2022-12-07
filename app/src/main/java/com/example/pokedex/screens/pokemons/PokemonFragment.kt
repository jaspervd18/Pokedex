package com.example.pokedex.screens.pokemons

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonBinding

class PokemonFragment : Fragment() {

    private lateinit var viewModel: PokemonViewModel

    private lateinit var binding: FragmentPokemonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon, container, false)

        // ViewModel
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)

        // Previous and Next button Listeners
        binding.nextpokemonButton.setOnClickListener {
            viewModel.nextPokemon()
        }
        binding.previouspokemonButton.setOnClickListener {
            viewModel.previousPokemon()
        }

        // Id and Name Observers
        viewModel.id.observe(viewLifecycleOwner, Observer { newId ->
            binding.pokemonId.text = newId.toString()
        })

        viewModel.name.observe(viewLifecycleOwner, Observer { newName ->
            binding.pokemonName.text = newName
        })

        return binding.root
    }

}