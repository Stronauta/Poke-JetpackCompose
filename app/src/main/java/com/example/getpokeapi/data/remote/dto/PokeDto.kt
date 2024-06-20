package com.example.getpokeapi.data.remote.dto

data class PokeDto(
    val name: String,
    val url: String

)

data class Container(
    val results: List<PokeDto>
)
