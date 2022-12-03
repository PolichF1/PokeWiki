package com.example.pokewiki.data

import com.example.pokewiki.data.model.PokemonResponse
import com.example.pokewiki.data.model.SinglePokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.pokewiki.utils.Constants.QUERY_LIMIT
import com.example.pokewiki.utils.Constants.QUERY_OFFSET
import com.example.pokewiki.utils.Constants.QUERY_POKEMON_NAME
import retrofit2.http.Path


interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemonResponse(
        @Query(QUERY_OFFSET) queryOffset: Int,
        @Query(QUERY_LIMIT) queryLimit: Int
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getSinglePokemonResponse(
        @Path(QUERY_POKEMON_NAME) queryName: String
    ): SinglePokemonResponse
}