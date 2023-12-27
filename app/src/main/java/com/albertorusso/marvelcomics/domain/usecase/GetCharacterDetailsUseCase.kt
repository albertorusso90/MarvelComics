package com.albertorusso.marvelcomics.domain.usecase

import com.albertorusso.marvelcomics.data.remote.repository.CharacterRepository
import com.albertorusso.marvelcomics.data.remote.model.CharacterDetails
import com.albertorusso.marvelcomics.data.remote.model.ImageData
import com.albertorusso.marvelcomics.domain.model.ImageItem
import com.albertorusso.marvelcomics.domain.model.MarvelCharacter
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(characterId: Int): MarvelCharacter? {
        val details = characterRepository.getCharacterDetails(characterId)
        val comics = characterRepository.getCharacterComics(characterId).orEmpty()
        val series = characterRepository.getCharacterSeries(characterId).orEmpty()
        val events = characterRepository.getCharacterEvents(characterId).orEmpty()
        
        return details?.let { transformToMarvelCharacter(it, comics, series, events) }
    }
    
    private fun transformToMarvelCharacter(
        characterDetails: CharacterDetails,
        comics: List<ImageData>,
        series: List<ImageData>,
        events: List<ImageData>
    ): MarvelCharacter {
        return with(characterDetails) {
            MarvelCharacter(
                id = id,
                name = name,
                image = "${thumbnail.path}.${thumbnail.extension}",
                comics = transformToImageItems(comics),
                events = transformToImageItems(series),
                series = transformToImageItems(events)
            )
        }
    }
    
    private fun transformToImageItems(items: List<ImageData>): List<ImageItem> {
        return items.map { ImageItem(
            name = it.title,
            image = "${it.thumbnail.path}.${it.thumbnail.extension}"
        )}
    }
}
