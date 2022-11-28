package com.example.pokedex.screens.pokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonBinding

class PokemonFragment : Fragment() {

    private lateinit var binding : FragmentPokemonBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

}