package com.albertorusso.marvelcomics.di

import com.albertorusso.marvelcomics.BuildConfig
import com.albertorusso.marvelcomics.data.remote.datasources.remote.RemoteDataSource
import com.albertorusso.marvelcomics.data.remote.datasources.remote.RemoteDataSourceImpl
import com.albertorusso.marvelcomics.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) // Set the OkHttpClient with interceptor
            .build()
            .create(ApiService::class.java)
    }
    
    class AuthInterceptor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val newUrl = originalRequest.url.newBuilder()
                .addQueryParameter("ts", "1")
                .addQueryParameter("apikey", BuildConfig.MARVEL_API_KEY) //THIS VALUE SHOULD BE ON local.properties file
                .addQueryParameter("hash", BuildConfig.MARVEL_API_HASH) //THIS VALUE SHOULD BE ON local.properties file
                .build()
            
            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()
            
            return chain.proceed(newRequest)
        }
    }
    
    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }
}
