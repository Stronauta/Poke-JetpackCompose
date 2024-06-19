package com.example.getpokeapi.data.repository

import com.example.getpokeapi.data.remote.PokeApi
import com.example.getpokeapi.data.remote.response.PokemonList
import com.example.getpokeapi.util.Resource
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val api: PokeApi



){

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>{
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception){
            return Resource.Error("An unknown error ocurred")
        }
        return Resource.Success(response)
    }

/*    suspend fun getPokeInfo(pokemonName: String): Resource<>{
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception){
            return Resource.Error("An unknown error ocurred")
        }
        return Resource.Success(response)
    }*/

}


