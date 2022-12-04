package com.example.pokewiki.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokewiki.data.ApiService
import com.example.pokewiki.data.model.PokemonResponse
import com.example.pokewiki.utils.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonPaging @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, PokemonResponse.PokemonResult>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonResponse.PokemonResult>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResponse.PokemonResult> {
        val pokemonOffset = params.key ?:Constants.OFFSET

        return try {
            val response = apiService.getPokemonResponse(pokemonOffset, params.loadSize)
            val pokemon = response.results

            PagingSource.LoadResult.Page(
                data = pokemon,
                prevKey = if (pokemonOffset == Constants.OFFSET) null else pokemonOffset - 20,
                nextKey = if (pokemon.isEmpty()) null else pokemonOffset + 20
            )
        } catch (exception: IOException) {
            PagingSource.LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}