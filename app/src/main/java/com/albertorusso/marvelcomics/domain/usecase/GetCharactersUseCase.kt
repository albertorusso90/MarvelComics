package com.albertorusso.marvelcomics.domain.usecase

import com.albertorusso.marvelcomics.data.remote.repository.CharacterRepository
import com.albertorusso.marvelcomics.data.remote.model.Character
import com.albertorusso.marvelcomics.domain.model.SimpleMarvelCharacter
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(name: String): List<SimpleMarvelCharacter> {
        return transformToSimplifiedCharactersList(characterRepository.getCharacters(name).orEmpty())
    }
    
    // Use case function to transform a list of detailed Characters to a list of simplified Characters
    private fun transformToSimplifiedCharactersList(characters: List<Character>): List<SimpleMarvelCharacter> {
        return characters.map { character ->
            SimpleMarvelCharacter(
                id = character.id,
                name = character.name,
                photo = "${character.thumbnail.path}.${character.thumbnail.extension}"
            )
        }
    }
}
