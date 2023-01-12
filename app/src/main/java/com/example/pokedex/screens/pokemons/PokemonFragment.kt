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
import timber.log.Timber

/**
 * This fragment shows the status of the current selected Pokemon.
 */
class PokemonFragment : Fragment() {

    /**
     * Initialize PokemonBinding.
     */
    private lateinit var binding: FragmentPokemonBinding

    /**
     * Initialize ViewModel.
     */
    private lateinit var viewModel: PokemonViewModel

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the PokemonFragment
     * to enable Data Binding to observe LiveData.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
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

        Timber.i("onCreate called")

        return binding.root
    }

    /**
     * Lifecycle method onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onStart called")
    }

    /**
     * Lifecycle method onStart
     */
    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
    }
}
