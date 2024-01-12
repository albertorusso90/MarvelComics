package com.albertorusso.marvelcomics.presentation.mappers

import com.albertorusso.marvelcomics.presentation.models.SimpleMarvelCharacter
import com.albertorusso.marvelcomics.domain.models.Character

/**
 * This class is used to map models from domain layer to presenter layer
 */
class Mapper {
    
    fun map(characterList: List<Character>): List<SimpleMarvelCharacter> {
        return characterList.map { mapSingleCharacter(it) }
    }
    
    private fun mapSingleCharacter(character: Character): SimpleMarvelCharacter {
        return SimpleMarvelCharacter(
            id = character.id,
            name = character.name,
            photo = "${character.thumbnail.path}.${character.thumbnail.extension}"
        )
    }
}
