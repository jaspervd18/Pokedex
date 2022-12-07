package com.example.pokedex.screens.pokemons

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonBinding
import com.example.pokedex.domain.Pokemon

class PokemonFragment : Fragment() {

    private lateinit var binding: FragmentPokemonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon, container, false)

        // ViewModel and ViewModelFactory
        val viewModelFactory = PokemonViewModelFactory()
//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PokemonViewModel::class.java)
        val viewModel : PokemonViewModel by viewModels{viewModelFactory}

        binding.pokemonViewModel = viewModel
        binding.setLifecycleOwner (this)

        // Id and Name Observers
//        viewModel.pokemonNr.observe(viewLifecycleOwner, Observer { newId ->
//            binding.pokemonId.text = newId.toString()
//        })
//        viewModel.name.observe(viewLifecycleOwner, Observer { newName ->
//            binding.pokemonName.text = newName
//        })

        return binding.root
    }

}