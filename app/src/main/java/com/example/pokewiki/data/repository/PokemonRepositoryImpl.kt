package com.example.pokewiki.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokewiki.data.api.ApiResponse
import com.example.pokewiki.data.api.ApiStates
import com.example.pokewiki.data.model.PokemonResult
import com.example.pokewiki.data.model.SinglePokemonResponse
import com.example.pokewiki.data.paging.PokemonPaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonPaging: PokemonPaging
): PokemonRepository {

    override fun getPokemonPaging(): Flow<PagingData<PokemonResult>> =
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = { pokemonPaging.getPokemonPaging() }
        ).flow

    override fun getPokemonDetails(queryName: String): Flow<ApiStates<SinglePokemonResponse>> =
        flow {
            emit(ApiStates.Loading())
            when (val response = pokemonPaging.getPokemonDetails(queryName).first()) {
                is ApiResponse.Success -> {
                    val data = response.data
                    emit(ApiStates.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(ApiStates.Error(response.errorMessage))
                }
                ApiResponse.Empty -> TODO()
            }
        }
}