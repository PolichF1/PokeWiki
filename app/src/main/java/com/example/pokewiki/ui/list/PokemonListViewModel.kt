package com.example.pokewiki.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokewiki.data.model.PokemonResponse
import com.example.pokewiki.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    repository: PokemonRepository,
) : ViewModel() {

    val paging = repository.getPokemonPaging().cachedIn(viewModelScope)

}