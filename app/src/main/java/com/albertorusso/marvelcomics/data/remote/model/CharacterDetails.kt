package com.albertorusso.marvelcomics.data.remote.model

data class CharacterDetails(
    val id: Int,
    val name: String,
    val description: String,
    val resourceURI: String,
    val thumbnail: Thumbnail,
    val comics: ComicList,
    val stories: StoryList,
    val events: EventList,
    val series: SeriesList
)
