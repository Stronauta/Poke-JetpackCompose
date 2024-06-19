package com.example.getpokeapi.presentation.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.getpokeapi.data.remote.dto.PokeDto


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeScreen(
    viewModel: PokeViewModel = hiltViewModel()
) {

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {
            Text("Pokemon List")
        })
    }
    ) {padding ->
        Column(modifier = Modifier.padding(padding)) {

            Row {

                Button(onClick = { viewModel.getPokes() }) {
                    Text("Buscar Pokemons")
                }

                Spacer(modifier = Modifier.weight(02f))

                Button(onClick = { viewModel.cleanList() }) {
                    Text("Limpiar Pantalla")
                }

            }

/*            if (viewModel.uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(viewModel.uiState.poke) { poke ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)) {
                            Text(text = poke.name, modifier = Modifier.weight(0.3f))
                        }
                    }
                }
            }*/
        }
    }
}

@Composable
fun PokemonScreenBody(pokemon: PokeDto) {

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