package com.example.pokewiki.utils

import android.widget.ImageView
import androidx.navigation.findNavController
import coil.load
import com.example.pokewiki.R
import com.example.pokewiki.ui.list.PokemonListFragmentDirections
import com.google.android.material.card.MaterialCardView

class BindingAdapter {

    companion object {
        @androidx.databinding.BindingAdapter("android:pokemonImage")
        @JvmStatic
        fun backDropPath(imageview: ImageView, url: String) {
            imageview.load(getPokemonImage(url)) {
                crossfade(200)
                error(R.drawable.ic_launcher_foreground)
            }
        }

        @androidx.databinding.BindingAdapter("android:navigate_to_pokemon_detail")
        @JvmStatic
        fun navigateToDetails(view: MaterialCardView, pokemonId: String) {
            view.setOnClickListener {
                val action = PokemonListFragmentDirections.actionPokemonListFragmentToDetailsFragment(pokemonId)
                view.findNavController().navigate(action)
            }
        }
    }
}