package com.albertorusso.marvelcomics.domain.usecase

import com.albertorusso.marvelcomics.data.remote.repository.CharacterRepository
import com.albertorusso.marvelcomics.data.remote.model.CharacterDetails
import com.albertorusso.marvelcomics.domain.model.ComicItem
import com.albertorusso.marvelcomics.domain.model.EventItem
import com.albertorusso.marvelcomics.domain.model.MarvelCharacter
import com.albertorusso.marvelcomics.domain.model.SeriesItem
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(characterId: Int): MarvelCharacter? {
        return characterRepository.getCharacterDetails(characterId)?.let { transformToMarvelCharacter(it) }
    }
    
    private fun transformToMarvelCharacter(characterDetails: CharacterDetails): MarvelCharacter {
        return with(characterDetails) {
            MarvelCharacter(
                id = id,
                name = name,
                image = "${thumbnail.path}.${thumbnail.extension}",
                comics = comics.items.map { ComicItem(it.name, it.resourceURI) },
                events = events.items.map { EventItem(it.name, it.resourceURI) },
                series = series.items.map { SeriesItem(it.name, it.resourceURI) }
            )
        }
    }
}
