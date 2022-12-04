package com.example.pokewiki.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokewiki.data.ApiService
import com.example.pokewiki.data.model.PokemonResponse
import com.example.pokewiki.data.paging.PokemonPaging
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getPokemonPaging(): Flow<PagingData<PokemonResponse.PokemonResult>> = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = {PokemonPaging(apiService)}
    ).flow
}