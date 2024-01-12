package com.albertorusso.marvelcomics.data.remote.datasources.remote

import com.albertorusso.marvelcomics.domain.models.Character
import com.albertorusso.marvelcomics.domain.models.CharacterDetails
import com.albertorusso.marvelcomics.domain.models.ImageData
import com.albertorusso.marvelcomics.domain.models.Result

interface RemoteDataSource {
    suspend fun getCharacters(name: String): Result<List<Character>>
    suspend fun getCharacterDetails(characterId: Int): Result<CharacterDetails>
    suspend fun getCharacterComics(characterId: Int): Result<List<ImageData>>
    suspend fun getCharacterSeries(characterId: Int): Result<List<ImageData>>
    suspend fun getCharacterEvents(characterId: Int): Result<List<ImageData>>
}
