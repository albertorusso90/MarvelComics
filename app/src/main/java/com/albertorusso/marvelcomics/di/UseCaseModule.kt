package com.albertorusso.marvelcomics.di

import com.albertorusso.marvelcomics.domain.repositories.CharacterRepository
import com.albertorusso.marvelcomics.domain.usecase.GetCharacterDetailsUseCase
import com.albertorusso.marvelcomics.domain.usecase.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetCharacterDetailsUseCase(characterRepository: CharacterRepository): GetCharacterDetailsUseCase {
        return GetCharacterDetailsUseCase(characterRepository)
    }
    
    @Provides
    @Singleton
    fun provideGetCharactersUseCase(characterRepository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepository)
    }
}
