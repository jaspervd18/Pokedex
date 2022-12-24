package com.example.pokedex.screens.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.R
import com.example.pokedex.database.favorites.FavoriteDatabase
import com.example.pokedex.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentFavoritesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = FavoriteDatabase.getInstance(application).favoriteDatabaseDao

        val viewModelFactory = FavoritesViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class.java]

        binding.favoritesViewModel = viewModel
        binding.lifecycleOwner = this

        // filling the list: favorites adapter
        val adapter = FavoritesAdapter(FavoritesListener { pokemonName ->
            Toast.makeText(context, "$pokemonName", Toast.LENGTH_SHORT).show()
        })
        binding.favoritesList.adapter = adapter

        // watch the data:
        viewModel.favorites.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

}