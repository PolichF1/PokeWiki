package com.example.pokewiki.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.pokewiki.R
import com.example.pokewiki.data.api.ApiStates
import com.example.pokewiki.data.model.SinglePokemonResponse
import com.example.pokewiki.databinding.FragmentDetailsBinding
import com.example.pokewiki.utils.formatId
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailsViewModel>()
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
        onSetupViewModel()
    }

    private fun onSetupViewModel() {
        val pokemonName: String = args.pokemonName
        viewModel.getPokemonPaging(pokemonName)
        viewModel.pokemonDetailResponse.observe(viewLifecycleOwner) {details ->
            when (details) {
                is ApiStates.Loading -> {}
                is ApiStates.Success -> { details.data?.let { initView(it) }}
                is ApiStates.Error -> {}
            }
        }
    }

    private fun initView(data: SinglePokemonResponse) {
        binding.apply {
            val pokemonImage = data.sprites?.other?.officialArtwork?.frontDefault
            pokemonDetailsImage.load(pokemonImage) {
                crossfade(200)
            }
            pokemonNumberTextView.text = formatId(data.id)
            pokemonDetailsName.text = data.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}