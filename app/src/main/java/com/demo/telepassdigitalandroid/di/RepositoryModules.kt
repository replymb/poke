package com.demo.telepassdigitalandroid.di

import com.demo.telepassdigitalandroid.network.IPokeService
import com.demo.telepassdigitalandroid.repository.PokeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModules {

    @Provides
    @Singleton
    fun providePokeRepository(pokeService: IPokeService) = PokeRepository(pokeService)
}