package com.albertorusso.marvelcomics.domain.usecase

import com.albertorusso.marvelcomics.domain.repositories.CharacterRepository
import com.albertorusso.marvelcomics.domain.models.Character
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(name: String): List<Character> {
        return characterRepository.getCharacters(name).orEmpty()
    }
}
