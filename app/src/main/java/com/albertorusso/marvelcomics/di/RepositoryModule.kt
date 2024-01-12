package com.albertorusso.marvelcomics.di

import com.albertorusso.marvelcomics.data.remote.datasources.remote.RemoteDataSource
import com.albertorusso.marvelcomics.network.ApiService
import com.albertorusso.marvelcomics.domain.repositories.CharacterRepository
import com.albertorusso.marvelcomics.data.remote.repositories.CharacterRepositoryImpl
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
    fun provideCharacterRepository(remoteDataSource: RemoteDataSource): CharacterRepository {
        return CharacterRepositoryImpl(remoteDataSource)
    }
}
