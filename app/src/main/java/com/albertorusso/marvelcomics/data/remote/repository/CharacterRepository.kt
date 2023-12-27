package com.albertorusso.marvelcomics.data.remote.repository

import com.albertorusso.marvelcomics.data.remote.model.Character
import com.albertorusso.marvelcomics.data.remote.model.CharacterDetails

interface CharacterRepository {
    suspend fun getCharacters(name: String): List<Character>?
    suspend fun getCharacterDetails(characterId: Int): CharacterDetails?
}
