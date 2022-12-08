package com.example.pokedex.screens.favorites

import android.app.Application
import androidx.constraintlayout.widget.ConstraintSet.Transform
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import kotlinx.coroutines.launch

class FavoritesViewModel(val database: FavoriteDatabaseDao, application: Application) :
    AndroidViewModel(application) {
    val favorites = database.getAllFavorites()

    val clearButtonVisible = Transformations.map(favorites) {
        it?.isNotEmpty()
    }

    private suspend fun clear() {
        database.clear()
    }

    fun onClear() {
        viewModelScope.launch {
            // Clear the database table.
            clear()
        }
    }
}