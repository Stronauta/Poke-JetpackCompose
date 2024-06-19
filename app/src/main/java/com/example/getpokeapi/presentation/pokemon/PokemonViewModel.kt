package com.example.getpokeapi.presentation.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.getpokeapi.data.remote.dto.PokeDto
import com.example.getpokeapi.data.repository.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokeRepository
) : ViewModel(){

    var uiState by mutableStateOf(PokeUiState())
        private set

    fun getPokemon(){
        uiState = uiState.copy(

        )
    }
}

data class PokeUiState(
    val poke: PokeDto? = null

)

