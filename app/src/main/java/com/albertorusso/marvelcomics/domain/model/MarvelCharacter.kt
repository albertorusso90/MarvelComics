package com.albertorusso.marvelcomics.domain.model

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val image: String,
    val comics: List<ComicItem>,
    val events: List<EventItem>,
    val series: List<SeriesItem>
)

data class ComicItem(
    val name: String,
    val image: String
)

data class EventItem(
    val name: String,
    val image: String
)

data class SeriesItem(
    val name: String,
    val image: String
)
