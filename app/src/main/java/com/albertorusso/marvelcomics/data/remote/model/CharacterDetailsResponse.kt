package com.albertorusso.marvelcomics.data.remote.model

data class CharacterDetailsResponse(
    val code: Int,
    val status: String,
    val data: CharacterDetailsData,
    val etag: String
)

data class CharacterDetailsData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterDetails>
)
