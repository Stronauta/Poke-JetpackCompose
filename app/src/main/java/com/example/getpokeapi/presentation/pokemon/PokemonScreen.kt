package com.example.getpokeapi.presentation.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.getpokeapi.data.remote.dto.PokeDto
import com.example.getpokeapi.ui.theme.GetPokeApiTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeScreen(
    viewModel: PokeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PokemonScreenBody(
        uiState = uiState,
        onRefreshPokemon = viewModel::getPokes
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreenBody(uiState: PokeUiState, onRefreshPokemon: () -> Unit) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text("Pokemon List")
            })
        }
    ) { padding ->

        Column(
            modifier = Modifier.padding(padding)
        ) {
            Button(
                onClick = onRefreshPokemon,
                colors = ButtonDefaults.buttonColors(),
            ) {
                Text("Buscar Pokemons")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ){
                    items(uiState.poke){poke ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            shape = MaterialTheme.shapes.medium,

                        ) {
                            PokemonItem(poke)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }

    }

}

@Composable
fun PokemonItem(pokemon: PokeDto){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        AsyncImage(
            model = pokemon.url,
            "Pokémon image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(8.dp)
        )

        Text(
            pokemon.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.fillMaxWidth())
    }
}


@Preview
@Composable
fun PokemonScreenBodyPreview() {

    GetPokeApiTheme {
        val pokemons = listOf(
            PokeDto(
                name = "Bulbasaur",
                url = "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            PokeDto(
                name = "Charmander",
                url = "https://pokeapi.co/api/v2/pokemon/4/"
            )
        )
        PokemonScreenBody(uiState = PokeUiState(poke = pokemons)) {
            
        }
    }
}