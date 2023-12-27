package com.albertorusso.marvelcomics.data.remote.model

data class CharacterImageResponse(
    val code: Int,
    val data: CharacterImagesData
)

data class CharacterImagesData(
    val results: List<ImageData>
)
