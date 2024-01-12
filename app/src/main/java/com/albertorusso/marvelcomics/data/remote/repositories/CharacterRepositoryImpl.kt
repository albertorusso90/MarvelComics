package com.albertorusso.marvelcomics.data.remote.repositories

import com.albertorusso.marvelcomics.network.ApiService
import com.albertorusso.marvelcomics.domain.models.Character
import com.albertorusso.marvelcomics.domain.models.CharacterDetails
import com.albertorusso.marvelcomics.domain.models.ImageData
import com.albertorusso.marvelcomics.domain.repositories.CharacterRepository


class CharacterRepositoryImpl(private val apiService: ApiService) : CharacterRepository {
    override suspend fun getCharacters(name: String): List<Character>? {
        val response = apiService.getCharacters(name)
        return if (response.isSuccessful) {
            response.body()?.data?.results
        } else {
            null // Return null on error
        }
    }
    
    override suspend fun getCharacterDetails(characterId: Int): CharacterDetails? {
        val response = apiService.getCharacterDetails(characterId)
        return if (response.isSuccessful) {
            response.body()?.data?.results?.firstOrNull()
        } else {
            null // Return null on error
        }
    }
    
    override suspend fun getCharacterComics(characterId: Int): List<ImageData>? {
        val response = apiService.getCharacterComics(characterId)
        return if (response.isSuccessful) {
            response.body()?.data?.results
        } else {
            null // Return null on error
        }
    }
    
    override suspend fun getCharacterSeries(characterId: Int): List<ImageData>? {
        val response = apiService.getCharacterSeries(characterId)
        return if (response.isSuccessful) {
            response.body()?.data?.results
        } else {
            null // Return null on error
        }
    }
    
    override suspend fun getCharacterEvents(characterId: Int): List<ImageData>? {
        val response = apiService.getCharacterEvents(characterId)
        return if (response.isSuccessful) {
            response.body()?.data?.results
        } else {
            null // Return null on error
        }
    }
}
