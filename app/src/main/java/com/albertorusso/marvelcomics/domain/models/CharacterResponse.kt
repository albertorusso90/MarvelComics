package com.albertorusso.marvelcomics.domain.models

data class CharacterResponse(
    val code: Int,
    val data: CharacterData,
)

data class CharacterData(
    val results: List<Character>
)
