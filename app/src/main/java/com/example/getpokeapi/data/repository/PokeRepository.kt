package com.example.getpokeapi.data.repository

import com.example.getpokeapi.data.remote.PokeApi
import com.example.getpokeapi.data.remote.dto.PokeDto
import com.example.getpokeapi.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val api: PokeApi
){


    suspend fun getPokemon(): Flow<Resource<List<PokeDto>>>  = flow{
        emit(Resource.Loading())
        try {
            val pokemones = api.getPokemons()
            emit(Resource.Success(pokemones))
        } catch (e: Exception){
            emit(Resource.Error("An unknown error ocurred"))
        }
    }

/*    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>{
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception){
            return Resource.Error("An unknown error ocurred")
        }
        return Resource.Success(response)
    }*/
}


