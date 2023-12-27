package com.albertorusso.marvelcomics.data.remote.repository

import com.albertorusso.marvelcomics.data.remote.model.Character
import com.albertorusso.marvelcomics.data.remote.model.CharacterDetails
import com.albertorusso.marvelcomics.data.remote.model.ImageData

interface CharacterRepository {
    suspend fun getCharacters(name: String): List<Character>?
    suspend fun getCharacterDetails(characterId: Int): CharacterDetails?
    suspend fun getCharacterComics(characterId: Int): List<ImageData>?
    suspend fun getCharacterSeries(characterId: Int): List<ImageData>?
    suspend fun getCharacterEvents(characterId: Int): List<ImageData>?
}
