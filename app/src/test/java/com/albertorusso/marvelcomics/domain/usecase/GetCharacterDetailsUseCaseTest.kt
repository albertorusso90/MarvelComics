package com.albertorusso.marvelcomics.domain.usecase

import com.albertorusso.marvelcomics.data.remote.model.CharacterDetails
import com.albertorusso.marvelcomics.data.remote.model.ImageData
import com.albertorusso.marvelcomics.data.remote.model.Thumbnail
import com.albertorusso.marvelcomics.data.remote.repository.CharacterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetCharacterDetailsUseCaseTest {
    
    private lateinit var characterRepository: CharacterRepository
    private lateinit var getCharacterDetailsUseCase: GetCharacterDetailsUseCase
    
    @Before
    fun setUp() {
        characterRepository = mock(CharacterRepository::class.java)
        getCharacterDetailsUseCase = GetCharacterDetailsUseCase(characterRepository)
    }
    
    @Test
    fun `invoke with valid characterId should return MarvelCharacter`() = runBlocking {
        // Given
        val characterId = 123
        val characterDetails = CharacterDetails(characterId, "Iron Man", "Guy that likes to iron", Thumbnail("path", "jpg"))
        val comics = listOf(
            ImageData("Comic 1", Thumbnail("path1", "jpg")),
            ImageData("Comic 2", Thumbnail("path2", "png"))
        )
        val series = listOf(
            ImageData("Series 1", Thumbnail("path3", "jpg")),
            ImageData("Series 2", Thumbnail("path4", "png"))
        )
        val events = listOf(
            ImageData("Event 1", Thumbnail("path5", "jpg")),
            ImageData("Event 2", Thumbnail("path6", "png"))
        )
        
        `when`(characterRepository.getCharacterDetails(characterId)).thenReturn(characterDetails)
        `when`(characterRepository.getCharacterComics(characterId)).thenReturn(comics)
        `when`(characterRepository.getCharacterSeries(characterId)).thenReturn(series)
        `when`(characterRepository.getCharacterEvents(characterId)).thenReturn(events)
        
        // When
        val result = getCharacterDetailsUseCase(characterId)
        
        // Then
        assertEquals(characterId, result?.id)
        assertEquals("Iron Man", result?.name)
        assertEquals("path.jpg", result?.image)
        assertEquals(2, result?.comics?.size)
        assertEquals("Comic 1", result?.comics?.get(0)?.name)
        assertEquals("path1.jpg", result?.comics?.get(0)?.image)
    }
    
    @Test
    fun `invoke with invalid characterId should return null`() = runBlocking {
        // Given
        val invalidCharacterId = -1
        `when`(characterRepository.getCharacterDetails(invalidCharacterId)).thenReturn(null)
        
        // When
        val result = getCharacterDetailsUseCase(invalidCharacterId)
        
        // Then
        assertEquals(null, result)
    }
    
}
