package com.example.pokewiki.data.paging

import com.example.pokewiki.utils.Constants.LIMIT
import com.example.pokewiki.utils.Constants.OFFSET
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokewiki.data.ApiService
import com.example.pokewiki.data.model.PokemonResponse
import com.example.pokewiki.data.model.PokemonResult
import com.example.pokewiki.utils.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonPagingImpl @Inject constructor(
    private val apiService: ApiService
) : PokemonPaging {

    override fun getPokemonPaging(): PagingSource<Int, PokemonResult> {
        return object : PagingSource<Int, PokemonResult>() {

            override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
                return state.anchorPosition
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
                return try {
                    val pokemonOffset = params.key ?:OFFSET
                    val response = apiService.getPokemonResponse(pokemonOffset, LIMIT)
                    val pokemon = response.results

                    LoadResult.Page(
                        data = pokemon,
                        prevKey = if (pokemonOffset == OFFSET) null else pokemonOffset - LIMIT,
                        nextKey = if (pokemon.isEmpty() || pokemon.size < LIMIT) null else pokemonOffset + LIMIT
                    )
                } catch (exception: IOException) {
                    LoadResult.Error(exception)
                } catch (exception: HttpException) {
                    LoadResult.Error(exception)
                }
            }

        }
    }
}