package com.albertorusso.marvelcomics.di

import com.albertorusso.marvelcomics.data.remote.ApiService
import com.albertorusso.marvelcomics.data.remote.repository.CharacterRepository
import com.albertorusso.marvelcomics.data.remote.repository.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCharacterRepository(apiService: ApiService): CharacterRepository {
        return CharacterRepositoryImpl(apiService)
    }
}
