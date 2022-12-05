package com.example.pokewiki.data.repository

import androidx.paging.PagingData
import com.example.pokewiki.data.model.PokemonResult
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonPaging(): Flow<PagingData<PokemonResult>>
}