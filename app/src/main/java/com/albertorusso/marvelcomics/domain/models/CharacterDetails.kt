package com.albertorusso.marvelcomics.domain.models

data class CharacterDetails(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)
