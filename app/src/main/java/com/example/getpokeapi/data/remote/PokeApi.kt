package com.example.getpokeapi.data.remote

import com.example.getpokeapi.data.remote.dto.Container
import retrofit2.http.GET

interface PokeApi {

    @GET("https://pokeapi.co/api/v2/pokemon?limit=151")
    suspend fun getPokemons(): Container

}