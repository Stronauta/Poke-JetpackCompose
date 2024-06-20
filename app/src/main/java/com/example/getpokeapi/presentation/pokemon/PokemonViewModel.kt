package com.example.getpokeapi.presentation.pokemon

import androidx.lifecycle.ViewModel
import com.example.getpokeapi.data.remote.dto.PokeDto
import com.example.getpokeapi.data.repository.PokeRepository
import com.example.getpokeapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class PokeViewModel @Inject constructor(
    private val repository: PokeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokeUiState())
    val uiState = _uiState.asStateFlow()

    fun getPokes() {

        repository.getPokemon().onEach { result ->
            when (result) {
                is Resource.Loading -> _uiState.update {
                    it.copy(isLoading = true)
                }

                is Resource.Success -> _uiState.update {
                    it.copy(
                        poke = result.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Error -> _uiState.update {
                    it.copy(
                        error = result.message,
                        isLoading = false
                    )
                }
            }
        }

    }

}

data class PokeUiState(
    val poke: List<PokeDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null

)