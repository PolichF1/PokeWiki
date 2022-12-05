package com.example.pokewiki.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokewiki.data.api.ApiStates
import com.example.pokewiki.data.model.SinglePokemonResponse
import com.example.pokewiki.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonDetailResponse = MutableLiveData<ApiStates<SinglePokemonResponse>>()
    val pokemonDetailResponse: LiveData<ApiStates<SinglePokemonResponse>> get() = _pokemonDetailResponse

    fun getPokemonPaging(queryName: String) {
        viewModelScope.launch {
            repository.getPokemonDetails(queryName)
                .onStart {

                }
                .catch {

                }
                .collect{
                    _pokemonDetailResponse.value = it
                }
        }
    }

}