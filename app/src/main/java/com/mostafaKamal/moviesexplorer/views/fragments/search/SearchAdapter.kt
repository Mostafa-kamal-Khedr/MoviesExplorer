package com.mostafaKamal.moviesexplorer.views.fragments.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mostafaKamal.moviesexplorer.R
import com.mostafaKamal.moviesexplorer.databinding.ItemSearchBinding
import com.mostafaKamal.moviesexplorer.util.extensions.loadImage
import com.movieapp.domain.model.Search
import kotlinx.coroutines.NonDisposableHandle.parent


class SearchAdapter : ListAdapter<Search, SearchAdapter.SearchViewHolder>(SearchDiffCallback) {


    inner class SearchViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: Search) {
            binding.searchTitle.text = content.title
            content.image?.let { binding.searchImage.loadImage("https://image.tmdb.org/t/p/w500$it",  R.drawable.dummy_poster, binding.root.context) }
            binding.searchImdb.text = content.imdb
            binding.searchAbout.text = content.description
            binding.constraint.setOnClickListener {
                it.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(content.id))
            }
        }
    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding)
    }

}

object SearchDiffCallback : DiffUtil.ItemCallback<Search>() {
    override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem == newItem
    }

}