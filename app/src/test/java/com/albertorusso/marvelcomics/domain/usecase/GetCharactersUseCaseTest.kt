package com.albertorusso.marvelcomics.domain.usecase

import com.albertorusso.marvelcomics.domain.models.Thumbnail
import com.albertorusso.marvelcomics.domain.repositories.CharacterRepository
import com.albertorusso.marvelcomics.domain.models.Character
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetCharactersUseCaseTest {
    
    private lateinit var characterRepository: CharacterRepository
    private lateinit var getCharactersUseCase: GetCharactersUseCase
    
    @Before
    fun setUp() {
        characterRepository = mock(CharacterRepository::class.java)
        getCharactersUseCase = GetCharactersUseCase(characterRepository)
    }
    
    @Test
    fun `invoke with valid name should return simplified characters list`() = runBlocking {
        // Given
        val charactersList = listOf(
            Character(1, "Iron Man", "Guy that likes to iron", Thumbnail("", "")),
            Character(2, "Captain America", "Guy that likes to get oil", Thumbnail("", ""))
        )
        
        `when`(characterRepository.getCharacters("Avengers")).thenReturn(charactersList)
        
        // When
        val result = getCharactersUseCase("Avengers")
        
        // Then
        assertEquals(2, result.size)
        assertEquals(1, result[0].id)
        assertEquals("Iron Man", result[0].name)
        assertEquals("Captain America", result[1].name)
    }
    
    @Test
    fun `invoke with empty name should return empty list`() = runBlocking {
        // Given
        `when`(characterRepository.getCharacters("")).thenReturn(emptyList())
        
        // When
        val result = getCharactersUseCase("")
        
        // Then
        assertEquals(0, result.size)
    }
}
