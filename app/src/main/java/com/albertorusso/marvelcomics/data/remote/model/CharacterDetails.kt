package com.albertorusso.marvelcomics.data.remote.model

data class CharacterDetails(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)
