package com.example.pokedex.screens.pokemons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.R
import com.example.pokedex.database.favorites.FavoriteDatabase
import com.example.pokedex.databinding.FragmentPokemonBinding

/**
 * This fragment shows the status of the current selected Pokemon.
 */
class PokemonFragment : Fragment() {

    lateinit var binding: FragmentPokemonBinding
    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon, container, false)

        // Get an instance of the appContext to setup the database
        val appContext = requireNotNull(this.activity).application
        val dataSource = FavoriteDatabase.getInstance(appContext).favoriteDatabaseDao

        // ViewModel and ViewModelFactory
        val viewModelFactory = PokemonViewModelFactory(dataSource, appContext)
        viewModel = ViewModelProvider(this, viewModelFactory)[PokemonViewModel::class.java]

        // Giving the binding access to the OverviewViewModel
        binding.pokemonViewModel = viewModel

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        viewModel.saveEvent.observe(viewLifecycleOwner) { saveEvent ->
            if (saveEvent) {
                viewModel.favoritePokemon()
                viewModel.saveEventDone()
            }
        }

        return binding.root
    }
}
