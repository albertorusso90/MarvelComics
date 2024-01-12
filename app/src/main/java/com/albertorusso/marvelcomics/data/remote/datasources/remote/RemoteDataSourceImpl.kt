package com.albertorusso.marvelcomics.data.remote.datasources.remote

import com.albertorusso.marvelcomics.domain.models.Result
import com.albertorusso.marvelcomics.domain.models.Character
import com.albertorusso.marvelcomics.domain.models.CharacterDetails
import com.albertorusso.marvelcomics.domain.models.ImageData
import com.albertorusso.marvelcomics.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) : RemoteDataSource {
    
    override suspend fun getCharacters(name: String): Result<List<Character>> {
        return try {
            val response = apiService.getCharacters(name)
            if (response.isSuccessful) {
                response.body()?.data?.results
                    ?.let { Result.Success(it) }
                    ?: Result.Error("No characters found.")
            } else {
                Result.Error("Failed to retrieve characters: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }
    
    override suspend fun getCharacterDetails(characterId: Int): Result<CharacterDetails> {
        return try {
            val response = apiService.getCharacterDetails(characterId)
            if (response.isSuccessful) {
                response.body()?.data?.results?.firstOrNull()
                    ?.let { Result.Success(it) }
                    ?: Result.Error("Character details not found.")
            } else {
                Result.Error("Failed to retrieve character details: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }
    
    override suspend fun getCharacterComics(characterId: Int): Result<List<ImageData>> {
        return try {
            val response = apiService.getCharacterComics(characterId)
            if (response.isSuccessful) {
                response.body()?.data
                    ?.let { Result.Success(it.results) }
                    ?: Result.Error("No comics found for the character.")
            } else {
                Result.Error("Failed to retrieve character comics: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }
    
    override suspend fun getCharacterSeries(characterId: Int): Result<List<ImageData>> {
        return try {
            val response = apiService.getCharacterSeries(characterId)
            if (response.isSuccessful) {
                response.body()?.data
                    ?.let { Result.Success(it.results) }
                    ?: Result.Error("No series found for the character.")
            } else {
                Result.Error("Failed to retrieve character series: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }
    
    override suspend fun getCharacterEvents(characterId: Int): Result<List<ImageData>> {
        return try {
            val response = apiService.getCharacterEvents(characterId)
            if (response.isSuccessful) {
                response.body()?.data
                    ?.let { Result.Success(it.results) }
                    ?: Result.Error("No events found for the character.")
            } else {
                Result.Error("Failed to retrieve character events: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }
}

