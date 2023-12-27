package com.albertorusso.marvelcomics.data.remote.model

data class Character(
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

data class Thumbnail(
    val path: String,
    val extension: String
)

data class ComicList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<ComicItem>
)

data class ComicItem(
    val resourceURI: String,
    val name: String
)

data class StoryList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<StoryItem>
)

data class StoryItem(
    val resourceURI: String,
    val name: String,
    val type: String
)

data class EventList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<EventItem>
)

data class EventItem(
    val resourceURI: String,
    val name: String
)

data class SeriesList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<SeriesItem>
)

data class SeriesItem(
    val resourceURI: String,
    val name: String
)
