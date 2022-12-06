package com.example.pokewiki.ui.itemAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokewiki.databinding.ItemLoadStateBinding

class ItemLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<ItemLoadStateAdapter.LoadStateViewHolder>(){

    inner class LoadStateViewHolder(private val binding: ItemLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryLoadButton.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryLoadButton.isVisible = loadState is LoadState.Error
                errorTextView.isVisible = loadState is LoadState.Error

                if (loadState is LoadState.Error) {
                    errorTextView.text = loadState.error.localizedMessage
                }
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }
}