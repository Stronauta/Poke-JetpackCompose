package com.example.getpokeapi.presentation.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.getpokeapi.data.remote.dto.PokeDto

@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel = hiltViewModel()
){
    val uiState = viewModel
}

@Composable
fun PokemonListBody(Pokemon: PokeDto){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        AsyncImage(
            model = Pokemon.imageUrl,
            "Pokémon imagen",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(8.dp)
        )

        Text(
            "#${Pokemon.id}",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(4.dp)
        )

        Text(
            Pokemon.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.fillMaxWidth())
    }
}


@Preview
@Composable
fun PokemonScreenPreview(){
    val poke = PokeDto(
        name = "Pikachu",
        url = "https://pokeapi.co/api/v2/pokemon/25/"
    )
    PokemonListBody(poke)
}