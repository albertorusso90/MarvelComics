package com.albertorusso.marvelcomics.data.remote.repositories

import com.albertorusso.marvelcomics.data.remote.datasources.remote.RemoteDataSource
import com.albertorusso.marvelcomics.domain.models.Result
import com.albertorusso.marvelcomics.domain.models.Character
import com.albertorusso.marvelcomics.domain.models.CharacterDetails
import com.albertorusso.marvelcomics.domain.models.ImageData
import com.albertorusso.marvelcomics.domain.repositories.CharacterRepository


class CharacterRepositoryImpl(private val remoteDataSource: RemoteDataSource) : CharacterRepository {
    
    override suspend fun getCharacters(name: String): Result<List<Character>> {
        return try {
            //i should retrieve from localDataSource, if is empty, then i should retrieve from remoteDataSource and save to localDataSource the values
            remoteDataSource.getCharacters(name)
        } catch (e: Exception) {
            Result.Error("Error getting characters: ${e.message}")
        }
    }
    
    override suspend fun getCharacterDetails(characterId: Int): Result<CharacterDetails> {
        return try {
            //i should retrieve from localDataSource, if is empty, then i should retrieve from remoteDataSource and save to localDataSource the values
            remoteDataSource.getCharacterDetails(characterId)
        } catch (e: Exception) {
            Result.Error("Error getting character details: ${e.message}")
        }
    }
    
    override suspend fun getCharacterComics(characterId: Int): Result<List<ImageData>> {
        return try {
            //i should retrieve from localDataSource, if is empty, then i should retrieve from remoteDataSource and save to localDataSource the values
            remoteDataSource.getCharacterComics(characterId)
        } catch (e: Exception) {
            Result.Error("Error getting character comics: ${e.message}")
        }
    }
    
    override suspend fun getCharacterSeries(characterId: Int): Result<List<ImageData>> {
        return try {
            //i should retrieve from localDataSource, if is empty, then i should retrieve from remoteDataSource and save to localDataSource the values
            remoteDataSource.getCharacterSeries(characterId)
        } catch (e: Exception) {
            Result.Error("Error getting character series: ${e.message}")
        }
    }
    
    override suspend fun getCharacterEvents(characterId: Int): Result<List<ImageData>> {
        return try {
            //i should retrieve from localDataSource, if is empty, then i should retrieve from remoteDataSource and save to localDataSource the values
            remoteDataSource.getCharacterEvents(characterId)
        } catch (e: Exception) {
            Result.Error("Error getting character events: ${e.message}")
        }
    }
}
