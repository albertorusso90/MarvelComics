package com.albertorusso.marvelcomics.data.remote.model

data class CharacterResponse(
    val code: Int,
    val data: CharacterData,
)

data class CharacterData(
    val results: List<Character>
)
