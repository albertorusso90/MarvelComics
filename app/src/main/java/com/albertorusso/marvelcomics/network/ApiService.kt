package com.albertorusso.marvelcomics.network

import com.albertorusso.marvelcomics.domain.models.CharacterDetailsResponse
import com.albertorusso.marvelcomics.domain.models.CharacterImageResponse
import com.albertorusso.marvelcomics.domain.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("nameStartsWith") nameStartsWith: String): Response<CharacterResponse>
    
    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterDetails(@Path("characterId") characterId: Int): Response<CharacterDetailsResponse>
    
    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getCharacterComics(@Path("characterId") characterId: Int): Response<CharacterImageResponse>
    
    @GET("/v1/public/characters/{characterId}/series")
    suspend fun getCharacterSeries(@Path("characterId") characterId: Int): Response<CharacterImageResponse>
    
    @GET("/v1/public/characters/{characterId}/events")
    suspend fun getCharacterEvents(@Path("characterId") characterId: Int): Response<CharacterImageResponse>
}
