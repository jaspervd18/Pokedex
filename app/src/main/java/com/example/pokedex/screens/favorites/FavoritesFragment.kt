package com.example.pokedex.screens.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.R
import com.example.pokedex.database.favorites.FavoriteDatabase
import com.example.pokedex.databinding.FragmentFavoritesBinding

/**
 * This fragment shows the favorite pokemon saved in the database
 */
class FavoritesFragment : Fragment() {

    /**
     * Initialize Favoritebinding.
     */
    private lateinit var binding: FragmentFavoritesBinding

    /**
     * Initialize ViewModel.
     */
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var adapter: FavoritesAdapter

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the FavoritesFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        // Get an instance of the appContext to setup the database
        val application = requireNotNull(this.activity).application
        val dataSource = FavoriteDatabase.getInstance(application).favoriteDatabaseDao

        // ViewModel and ViewModelFactory
        val viewModelFactory = FavoritesViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class.java]

        // Giving the binding access to the OverviewViewModel
        binding.favoritesViewModel = viewModel

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Filling the list with the favorites adapter
        adapter = FavoritesAdapter(
            FavoritesListener { pokemonName ->
                Toast.makeText(context, pokemonName, Toast.LENGTH_SHORT).show()
            }
        )
        binding.favoritesList.adapter = adapter

        // Observe the data
        viewModel.favorites.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }
}
