package com.albertorusso.marvelcomics.data.remote.model

data class CharacterImageResponse(
    val code: Int,
    val status: String,
    val data: CharacterImagesData,
    val etag: String
)

data class CharacterImagesData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<ImageData>
)
