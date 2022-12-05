package com.example.pokewiki.data.paging

import androidx.paging.PagingSource
import com.example.pokewiki.data.model.PokemonResponse
import com.example.pokewiki.data.model.PokemonResult

interface PokemonPaging {

    fun getPokemonPaging(): PagingSource<Int, PokemonResult>

}