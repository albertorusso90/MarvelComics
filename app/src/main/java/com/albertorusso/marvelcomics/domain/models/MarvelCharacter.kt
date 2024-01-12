package com.albertorusso.marvelcomics.domain.models

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val image: String,
    val comics: List<ImageItem>,
    val events: List<ImageItem>,
    val series: List<ImageItem>
)

data class ImageItem(
    val name: String,
    val image: String
)
