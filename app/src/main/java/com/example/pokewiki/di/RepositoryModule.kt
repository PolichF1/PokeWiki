package com.example.pokewiki.di

import com.example.pokewiki.data.repository.PokemonRepository
import com.example.pokewiki.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePokemonRepository(repositoryImpl: PokemonRepositoryImpl): PokemonRepository

}