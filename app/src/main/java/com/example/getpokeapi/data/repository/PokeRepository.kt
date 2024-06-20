package com.example.getpokeapi.data.repository

import android.util.Log
import com.example.getpokeapi.data.remote.PokeApi
import com.example.getpokeapi.data.remote.dto.PokeDto
import com.example.getpokeapi.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val api: PokeApi
){

     fun getPokemon(): Flow<Resource<List<PokeDto>>>  = flow{
        emit(Resource.Loading())
        try {
            val container = api.getPokemons()
            emit(Resource.Success(container.results))
        } catch (e: Exception){
            Log.e("PokeRepository", "getPokemon: ${e.message}" )
            emit(Resource.Error("An unknown error ocurred"))
        }
    }

}


