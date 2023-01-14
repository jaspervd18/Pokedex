package com.example.pokedex

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.pokedex.database.favorites.DatabaseFavorite
import com.example.pokedex.database.favorites.FavoriteDatabase
import com.example.pokedex.database.favorites.FavoriteDatabaseDao
import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */
@RunWith(AndroidJUnit4::class)
class FavoriteDatabaseTest {

    private lateinit var favoriteDao: FavoriteDatabaseDao
    private lateinit var db: FavoriteDatabase

    @Before
    fun createDb() {
        Log.i("before", "running before")
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, FavoriteDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        favoriteDao = db.favoriteDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetFavorite() = runBlocking {
        val databaseFavorite = DatabaseFavorite()
        favoriteDao.insert(databaseFavorite)
        val lastPokemon = favoriteDao.getLastPokemon()
        assertEquals(lastPokemon?.pokemonName, "")
    }
}
