package com.example.pokedex.screens.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pokedex.database.favorites.FavoriteDatabaseDao

class FavoritesViewModel(val database: FavoriteDatabaseDao, application: Application) :
    AndroidViewModel(application) {
    val favorites = database.getAllFavorites()
}