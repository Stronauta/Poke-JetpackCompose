package com.example.getpokeapi.data.remote

import retrofit2.http.Query
import com.example.getpokeapi.data.remote.dto.PokeDto
import com.example.getpokeapi.data.remote.response.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ) : PokemonList

    @GET("pokemon?limit=151")
    suspend fun getPokemons(): List<PokeDto>

}