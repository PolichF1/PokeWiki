package com.example.pokewiki.utils

import android.widget.ImageView
import coil.load
import com.example.pokewiki.R

class BindingAdapter {

    companion object {
        @androidx.databinding.BindingAdapter("android:pokemonImage")
        @JvmStatic
        fun backDropPath(imageview: ImageView, url: String) {
            imageview.load(url) {
                crossfade(200)
                error(R.drawable.ic_launcher_foreground)
            }
        }
    }
}