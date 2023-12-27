package com.albertorusso.marvelcomics.data.remote.model

data class CharacterResponse(
    val code: Int,
    val status: String,
    val data: CharacterData,
    val etag: String
)

data class CharacterData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)
