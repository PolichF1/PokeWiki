package com.example.pokewiki.data.repository

import androidx.paging.PagingData
import com.example.pokewiki.data.api.ApiStates
import com.example.pokewiki.data.model.PokemonResult
import com.example.pokewiki.data.model.SinglePokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonPaging(): Flow<PagingData<PokemonResult>>
    fun getPokemonDetails(queryName: String) : Flow<ApiStates<SinglePokemonResponse>>
}