package com.example.pokedex.screens.pokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.R
import com.example.pokedex.database.favorites.FavoriteDatabase
import com.example.pokedex.databinding.FragmentPokemonBinding

class PokemonFragment : Fragment() {

    lateinit var binding: FragmentPokemonBinding
    lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon, container, false)

        //Get an instance of the appContext to setup the database
        val appContext = requireNotNull(this.activity).application
        val dataSource = FavoriteDatabase.getInstance(appContext).favoriteDatabaseDao

        // ViewModel and ViewModelFactory
        val viewModelFactory = PokemonViewModelFactory(dataSource, appContext)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonViewModel::class.java)

        binding.pokemonViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.saveEvent.observe(viewLifecycleOwner, Observer {
                saveEvent -> if(saveEvent){
            viewModel.favoritePokemon(binding.pokemonName.text.toString())
            //navigate back to the joke screen
            viewModel.saveEventDone()
        }
        })

        return binding.root
    }

}