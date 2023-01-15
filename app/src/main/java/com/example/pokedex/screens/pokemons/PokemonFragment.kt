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

const val KEY_COUNTER = "key_counter"

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

        // Setting the current pokemon to the last visited one
        if (savedInstanceState != null) {
            viewModel.counter = savedInstanceState.getInt(KEY_COUNTER)
        }

        Timber.i("onCreateView called")

        return binding.root
    }

    /**
     * onSaveInstanceState to retrieve the last visited pokemon when returning to the fragment
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, viewModel.counter)
        Timber.i("onSaveInstanceState called")
    }

    /**
     * Lifecycle method onStart
     */
    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    /**
     * Lifecycle method onStart
     */
    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }

    /**
     * Lifecycle method onPause
     */
    override fun onPause() {
        super.onPause()
        Timber.i("onPause called")
    }

    /**
     * Lifecycle method onResume
     */
    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
    }
}
