package com.albertorusso.marvelcomics.data.remote.repository

import com.albertorusso.marvelcomics.data.remote.ApiService
import com.albertorusso.marvelcomics.data.remote.model.Thumbnail
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import com.albertorusso.marvelcomics.data.remote.model.Character
import com.albertorusso.marvelcomics.data.remote.model.CharacterData
import com.albertorusso.marvelcomics.data.remote.model.CharacterDetails
import com.albertorusso.marvelcomics.data.remote.model.CharacterDetailsData
import com.albertorusso.marvelcomics.data.remote.model.CharacterDetailsResponse
import com.albertorusso.marvelcomics.data.remote.model.CharacterImageResponse
import com.albertorusso.marvelcomics.data.remote.model.CharacterImagesData
import com.albertorusso.marvelcomics.data.remote.model.CharacterResponse
import com.albertorusso.marvelcomics.data.remote.model.ImageData
import org.mockito.ArgumentMatchers.anyInt

class CharacterRepositoryImplTest {
    
    private lateinit var apiService: ApiService
    private lateinit var characterRepository: CharacterRepositoryImpl
    
    @Before
    fun setUp() {
        apiService = mock(ApiService::class.java)
        characterRepository = CharacterRepositoryImpl(apiService)
    }
    
    @Test
    fun `getCharacters with valid name should return character list`() = runBlocking {
        // Given
        val characterList = listOf(
            Character(1, "Iron Man", "Guy who likes to iron", Thumbnail("path", "jpg")),
            Character(2, "Captain America", "Guy who likes to take oil", Thumbnail("path2", "png"))
        )
        val successResponse = Response.success(CharacterResponse(200, CharacterData(characterList)))
        
        `when`(apiService.getCharacters(anyString())).thenReturn(successResponse)
        
        // When
        val result = characterRepository.getCharacters("Avengers")
        
        // Then
        assertEquals(2, result?.size) // Modify this based on the number of characters you've provided
        assertEquals(1, result?.get(0)?.id)
        assertEquals("Iron Man", result?.get(0)?.name)
    }
    
    @Test
    fun `getCharacters with invalid name should return null`() = runBlocking {
        // Given
        val errorResponse = Response.error<CharacterResponse>(
            400,
            ResponseBody.create(null, "Error")
        )
    
        `when`(apiService.getCharacters(anyString())).thenReturn(errorResponse)
    
        // When
        val result = characterRepository.getCharacters("InvalidName")
    
        // Then
        assertEquals(null, result)
    }
    
    @Test
    fun `getCharacterDetails with valid id should return character details`() = runBlocking {
        // Given
        val characterDetails = listOf(
            CharacterDetails(1, "Iron Man", "Guy who likes to iron", Thumbnail("path", "jpg")),
        )
        val successResponse = Response.success(CharacterDetailsResponse(200, CharacterDetailsData(characterDetails)))
        
        `when`(apiService.getCharacterDetails(103)).thenReturn(successResponse)
        
        // When
        val result = characterRepository.getCharacterDetails(103)
        
        // Then
        assertEquals(1, result?.id)
        assertEquals("Iron Man", result?.name)
    }
    
    @Test
    fun `getCharacterDetails with invalid id should return null`() = runBlocking {
        // Given
        val errorResponse = Response.error<CharacterDetailsResponse>(
            400,
            ResponseBody.create(null, "Error")
        )
        
        `when`(apiService.getCharacterDetails(anyInt())).thenReturn(errorResponse)
        
        // When
        val result = characterRepository.getCharacterDetails(-1)
        
        // Then
        assertEquals(null, result)
    }
    
    @Test
    fun `getCharacterComics with valid id should return character comics`() = runBlocking {
        // Given
        val comicList = listOf(
            ImageData("image1", Thumbnail("path1", "jpg")),
            ImageData("image2", Thumbnail("path2", "jpg"))
        )
        
        val successResponse = Response.success(CharacterImageResponse(200, CharacterImagesData(comicList)))
        
        `when`(apiService.getCharacterComics(103)).thenReturn(successResponse)
        
        // When
        val result = characterRepository.getCharacterComics(103)
        
        // Then
        assertEquals("image1", result?.get(0)?.title)
        assertEquals("image2", result?.get(1)?.title)
        assertEquals(2, result?.size)
    }
    
    @Test
    fun `getCharacterComics with invalid id should return null`() = runBlocking {
        // Given
        val errorResponse = Response.error<CharacterImageResponse>(
            400,
            ResponseBody.create(null, "Error")
        )
        
        `when`(apiService.getCharacterComics(anyInt())).thenReturn(errorResponse)
        
        // When
        val result = characterRepository.getCharacterComics(-1)
        
        // Then
        assertEquals(null, result)
    }
    
    @Test
    fun `getCharacterSeries with valid id should return character series`() = runBlocking {
        // Given
        val seriesList = listOf(
            ImageData("image1", Thumbnail("path1", "jpg")),
            ImageData("image2", Thumbnail("path2", "jpg"))
        )
        
        val successResponse = Response.success(CharacterImageResponse(200, CharacterImagesData(seriesList)))
        
        `when`(apiService.getCharacterSeries(103)).thenReturn(successResponse)
        
        // When
        val result = characterRepository.getCharacterSeries(103)
        
        // Then
        assertEquals("image1", result?.get(0)?.title)
        assertEquals("image2", result?.get(1)?.title)
        assertEquals(2, result?.size)
    }
    
    @Test
    fun `getCharacterSeries with invalid id should return null`() = runBlocking {
        // Given
        val errorResponse = Response.error<CharacterImageResponse>(
            400,
            ResponseBody.create(null, "Error")
        )
        
        `when`(apiService.getCharacterSeries(anyInt())).thenReturn(errorResponse)
        
        // When
        val result = characterRepository.getCharacterSeries(-1)
        
        // Then
        assertEquals(null, result)
    }
    
    @Test
    fun `getCharacterEvents with valid id should return character events`() = runBlocking {
        // Given
        val seriesList = listOf(
            ImageData("image1", Thumbnail("path1", "jpg")),
            ImageData("image2", Thumbnail("path2", "jpg"))
        )
        
        val successResponse = Response.success(CharacterImageResponse(200, CharacterImagesData(seriesList)))
        
        `when`(apiService.getCharacterEvents(103)).thenReturn(successResponse)
        
        // When
        val result = characterRepository.getCharacterEvents(103)
        
        // Then
        assertEquals("image1", result?.get(0)?.title)
        assertEquals("image2", result?.get(1)?.title)
        assertEquals(2, result?.size)
    }
    
    @Test
    fun `getCharacterEvents with invalid id should return null`() = runBlocking {
        // Given
        val errorResponse = Response.error<CharacterImageResponse>(
            400,
            ResponseBody.create(null, "Error")
        )
        
        `when`(apiService.getCharacterEvents(anyInt())).thenReturn(errorResponse)
        
        // When
        val result = characterRepository.getCharacterEvents(-1)
        
        // Then
        assertEquals(null, result)
    }
}
