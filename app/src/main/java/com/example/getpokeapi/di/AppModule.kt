package com.example.getpokeapi.di

import com.example.getpokeapi.data.remote.PokeApi
import com.example.getpokeapi.data.repository.PokeRepository
import com.example.getpokeapi.util.Constants.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providePokeRepo(
        api: PokeApi
    ) = PokeRepository(api)



    @Provides
    @Singleton
    fun providPoke(moshi: Moshi): PokeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PokeApi::class.java)
    }
}