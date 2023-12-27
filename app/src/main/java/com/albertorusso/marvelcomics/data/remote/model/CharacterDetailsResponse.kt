package com.albertorusso.marvelcomics.data.remote.model

data class CharacterDetailsResponse(
    val code: Int,
    val data: CharacterDetailsData
)

data class CharacterDetailsData(
    val results: List<CharacterDetails>
)
