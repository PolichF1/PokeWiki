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
import com.example.pokewiki.data.model.TypesItem
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
            pokemonNumberTextView.text = getString(R.string.pokemon_number_format, data.id)
            pokemonDetailsName.text = data.name
            heightDetail.text = getString(R.string.pokemon_format_height, (data.height.times(10)))
            weightDetail.text = getString(R.string.pokemon_format_weight, (data.weight.div(10.0)))
            pokemonTypeOne.text = data.types[0].type.name
            setPokemonTypes(data.types)

        }
    }

    private fun setPokemonTypes(types: List<TypesItem>) {
        with(binding) {
            if (types.size > 1) {
                pokemonTypeTwo.text = types[1].type.name
                pokemonTypeTwo.visibility = View.VISIBLE
            } else {
                pokemonTypeTwo.visibility = View.GONE
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}