package com.example.getpokeapi.presentation.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getpokeapi.data.remote.dto.PokeDto
import com.example.getpokeapi.data.repository.PokeRepository
import com.example.getpokeapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokeViewModel @Inject constructor(
    private val repository: PokeRepository
) : ViewModel() {

    var uiState by mutableStateOf(PokeUiState())
        private set

    init {
        viewModelScope.launch {
        }
    }

    fun getPokes() {
        viewModelScope.launch {
            repository.getPokemon().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        uiState = PokeUiState(isLoading = true)
                    }

                    is Resource.Success -> {
                        uiState = PokeUiState(
                            isLoading = false,
                            poke = result.data ?: emptyList()
                        )
                    }

                    is Resource.Error -> {
                        uiState = PokeUiState(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

    fun cleanList(){
        uiState = PokeUiState()
    }
}

data class PokeUiState(
    val poke: List<PokeDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null

)