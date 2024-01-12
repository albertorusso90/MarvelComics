package com.albertorusso.marvelcomics.domain.models

data class CharacterDetailsResponse(
    val code: Int,
    val data: CharacterDetailsData
)

data class CharacterDetailsData(
    val results: List<CharacterDetails>
)
