package com.example.pokedex.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://pokeapi.co/api/v2/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getPokemonAsync] method
 */
interface PokemonApiService {
    /**
     * Returns a Coroutine [Deferred] of [Pokemon] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "pokemon/{id}" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("pokemon/{id}")
    fun getPokemonAsync(@Path("id") id: Int): Deferred<Pokemon>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object PokemonApi {
    val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}
