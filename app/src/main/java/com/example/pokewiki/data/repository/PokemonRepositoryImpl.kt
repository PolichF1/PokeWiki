package com.example.pokewiki.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokewiki.data.model.PokemonResult
import com.example.pokewiki.data.paging.PokemonPaging
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonPaging: PokemonPaging
): PokemonRepository {

    override fun getPokemonPaging(): Flow<PagingData<PokemonResult>> =
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {pokemonPaging.getPokemonPaging()}
        ).flow
}