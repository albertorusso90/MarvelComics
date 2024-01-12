package com.albertorusso.marvelcomics.domain.usecase

import com.albertorusso.marvelcomics.domain.repositories.CharacterRepository
import com.albertorusso.marvelcomics.domain.models.Result
import com.albertorusso.marvelcomics.domain.models.CharacterDetails
import com.albertorusso.marvelcomics.domain.models.ImageData
import com.albertorusso.marvelcomics.domain.models.ImageItem
import com.albertorusso.marvelcomics.domain.models.MarvelCharacter
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(characterId: Int): Result<MarvelCharacter> {
        return try {
            val detailsResult = characterRepository.getCharacterDetails(characterId)
            val comicsResult = characterRepository.getCharacterComics(characterId)
            val seriesResult = characterRepository.getCharacterSeries(characterId)
            val eventsResult = characterRepository.getCharacterEvents(characterId)
            
            if (detailsResult is Result.Success) {
                val details = detailsResult.data
                val comics = (comicsResult as? Result.Success)?.data ?: emptyList()
                val series = (seriesResult as? Result.Success)?.data ?: emptyList()
                val events = (eventsResult as? Result.Success)?.data ?: emptyList()
                
                Result.Success(transformToMarvelCharacter(details, comics, series, events))
            } else {
                Result.Error("Failed to retrieve character details: ${(detailsResult as? Result.Error)?.message}")
            }
        } catch (e: Exception) {
            Result.Error("Error getting character details: ${e.message}")
        }
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
        return items.map { ImageItem(name = it.title, image = "${it.thumbnail.path}.${it.thumbnail.extension}") }
    }
}
