package com.example.getpokeapi.data.remote

import com.example.getpokeapi.data.remote.dto.PokeDto
import retrofit2.http.GET

interface PokeApi {

    @GET("pokemon?limit=151")
    suspend fun getPokemons(): List<PokeDto>
}