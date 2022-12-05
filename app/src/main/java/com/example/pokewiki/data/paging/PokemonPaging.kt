package com.example.pokewiki.data.paging

import androidx.paging.PagingSource
import com.example.pokewiki.data.api.ApiResponse
import com.example.pokewiki.data.model.PokemonResponse
import com.example.pokewiki.data.model.PokemonResult
import com.example.pokewiki.data.model.SinglePokemonResponse
import kotlinx.coroutines.flow.Flow


interface PokemonPaging {

    fun getPokemonPaging(): PagingSource<Int, PokemonResult>
    fun getPokemonDetails(queryName: String): Flow<ApiResponse<SinglePokemonResponse>>

}