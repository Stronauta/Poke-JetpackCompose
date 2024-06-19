package com.example.getpokeapi.data.remote

import com.example.getpokeapi.data.remote.dto.PokeDto
import retrofit2.http.GET

interface PokeApi {

/*    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ) : PokemonList*/

    @GET("pokemon?limit=151")
    suspend fun getPokemons(): List<PokeDto>

}