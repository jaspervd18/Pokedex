package com.example.pokedex.screens.pokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonBinding

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
        val viewModel : PokemonViewModel by viewModels{viewModelFactory}

        binding.pokemonViewModel = viewModel
        binding.setLifecycleOwner (this)

        return binding.root
    }

}