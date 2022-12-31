package com.example.pokedex.screens.home

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setOnClickListeners()

        setHasOptionsMenu(true)

        return binding.root
    }

    /**
     * Inflates the overflow menu.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Navigates to selected component
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!, requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun setOnClickListeners() {
        binding.gotopokedexButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_pokemonFragment)
        )
        binding.gotofavoritesButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_favoritesFragment)
        )
    }

}
