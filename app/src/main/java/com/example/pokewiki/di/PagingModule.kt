package com.example.pokewiki.di

import com.example.pokewiki.data.paging.PokemonPaging
import com.example.pokewiki.data.paging.PokemonPagingImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class PagingModule {

    @Binds
    abstract fun providePokemonPaging(pokemonPagingImpl: PokemonPagingImpl): PokemonPaging

}