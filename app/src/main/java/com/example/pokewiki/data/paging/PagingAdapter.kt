package com.example.pokewiki.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokewiki.data.model.PokemonResult
import com.example.pokewiki.databinding.ItemPokemonListBinding


class PagingAdapter :
    PagingDataAdapter<PokemonResult, PagingAdapter.PokemonViewHolder>(POKEMON_COMPARATOR) {

    inner class PokemonViewHolder(private val binding: ItemPokemonListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonResult: PokemonResult) {
            binding.pokemonResult = pokemonResult
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentPokemon = getItem(position)
        if (currentPokemon != null) {
            holder.bind(currentPokemon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PokemonViewHolder(binding)
    }

    companion object {
        private val POKEMON_COMPARATOR = object : DiffUtil.ItemCallback<PokemonResult>() {
            override fun areItemsTheSame(oldItem: PokemonResult, newItem: PokemonResult) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: PokemonResult, newItem: PokemonResult) =
                oldItem == newItem
        }
    }
}


