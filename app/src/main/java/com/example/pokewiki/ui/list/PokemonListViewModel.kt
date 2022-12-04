package com.example.pokewiki.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.pokewiki.data.model.PokemonResponse
import com.example.pokewiki.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonPagingData = MutableLiveData<PagingData<PokemonResponse.PokemonResult>>()
    val pokemonPagingData = _pokemonPagingData

    fun getPokemonPagingSource() {
        viewModelScope.launch {
            repository.getPokemonPaging()
                .collect{
                    _pokemonPagingData.value = it
                }
        }
    }

}