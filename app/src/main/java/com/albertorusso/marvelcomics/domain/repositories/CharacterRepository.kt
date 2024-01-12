package com.albertorusso.marvelcomics.domain.repositories

import com.albertorusso.marvelcomics.domain.models.Character
import com.albertorusso.marvelcomics.domain.models.CharacterDetails
import com.albertorusso.marvelcomics.domain.models.ImageData

interface CharacterRepository {
    suspend fun getCharacters(name: String): List<Character>?
    suspend fun getCharacterDetails(characterId: Int): CharacterDetails?
    suspend fun getCharacterComics(characterId: Int): List<ImageData>?
    suspend fun getCharacterSeries(characterId: Int): List<ImageData>?
    suspend fun getCharacterEvents(characterId: Int): List<ImageData>?
}
