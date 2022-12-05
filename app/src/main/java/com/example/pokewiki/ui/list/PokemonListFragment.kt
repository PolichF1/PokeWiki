package com.example.pokewiki.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.pokewiki.R
import com.example.pokewiki.data.paging.PagingAdapter
import com.example.pokewiki.databinding.FragmentPokemonListBinding
import com.example.pokewiki.ui.load.ItemLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PokemonListViewModel>()
    private val pagingAdapter by lazy { PagingAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonListBinding.bind(view)
        
        onSetupRecyclerView()
        onSetupViewModel()
    }
    
    private fun onSetupRecyclerView() {
        binding.apply {
            pokemonsRV.setHasFixedSize(true)
            pokemonsRV.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                header = ItemLoadStateAdapter { pagingAdapter.retry() },
                footer = ItemLoadStateAdapter { pagingAdapter.retry() }
            )
            listRetryButton.setOnClickListener { pagingAdapter.retry() }

            pagingAdapter.addLoadStateListener { loadState ->
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                errorTextView.isVisible = loadState.source.refresh is LoadState.Error
                listRetryButton.isVisible = loadState.source.refresh is LoadState.Error
            }

            }
    }

    private fun onSetupViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.paging.collect{
                pagingAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}