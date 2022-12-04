package com.example.pokewiki.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.pokewiki.R
import com.example.pokewiki.data.paging.PagingAdapter
import com.example.pokewiki.databinding.FragmentPokemonListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PokemonListViewModel>()
    private lateinit var pagingAdapter: PagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokemonListBinding.bind(view)
        
        onSetupRecyclerView()
        onSetupViewModel()
    }
    
    private fun onSetupRecyclerView() {
        binding.pokemonsRV.apply { 
            pagingAdapter = PagingAdapter()
            adapter = pagingAdapter
        }
    }
    
    private fun onSetupViewModel() {
        viewModel.getPokemonPagingSource()
        viewModel.pokemonPagingData.observe(viewLifecycleOwner) {
            pagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            binding.pokemonsRV.visibility = View.VISIBLE
        }
    }
}