package com.example.pokewiki.data.paging

import com.example.pokewiki.utils.Constants.LIMIT
import com.example.pokewiki.utils.Constants.OFFSET
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokewiki.data.ApiService
import com.example.pokewiki.data.api.ApiResponse
import com.example.pokewiki.data.model.PokemonResult
import com.example.pokewiki.data.model.SinglePokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

                    val prevKey = if (pokemonOffset == OFFSET) null else pokemonOffset - LIMIT
                    val nextKey = if (pokemon.size < LIMIT || pokemon.isEmpty()) null else pokemonOffset + LIMIT

                    LoadResult.Page(
                        data = pokemon,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                } catch (exception: IOException) {
                    LoadResult.Error(exception)
                } catch (exception: HttpException) {
                    LoadResult.Error(exception)
                }
            }

        }
    }

    override suspend fun getPokemonDetails(queryName: String): Flow<ApiResponse<SinglePokemonResponse>> {
        return flow {
            try {
                val response = apiService.getSinglePokemonResponse(queryName)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}