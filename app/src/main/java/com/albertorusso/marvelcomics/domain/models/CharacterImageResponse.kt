package com.albertorusso.marvelcomics.domain.models

data class CharacterImageResponse(
    val code: Int,
    val data: CharacterImagesData
)

data class CharacterImagesData(
    val results: List<ImageData>
)
