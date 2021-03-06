package com.mostafaKamal.moviesexplorer.views.fragments.movietrend.latestmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.mostafaKamal.moviesexplorer.databinding.FragmentLatestMoviesBinding
import com.mostafaKamal.moviesexplorer.datasource.network.api.NetworkState
import com.mostafaKamal.moviesexplorer.util.ConnectivityUtil
import com.mostafaKamal.moviesexplorer.util.extensions.hide
import com.mostafaKamal.moviesexplorer.util.extensions.show
import com.mostafaKamal.moviesexplorer.util.observeChange
import com.mostafaKamal.moviesexplorer.views.general.MoviesAdapter


class LatestMoviesFragment : Fragment() {
    private var _binding: FragmentLatestMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LatestMoviesViewModel by activityViewModels()
    private var isConnected: Boolean = true
    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLatestMoviesBinding.inflate(inflater, container, false)
        context ?: return binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkInternetStatus()

        updateUI(adapter)
        setUpRefresher(adapter)
    }

    private fun checkInternetStatus() {
        isConnected = ConnectivityUtil.isConnected(context)
        if (!isConnected)
            Toast.makeText(
                context?.applicationContext,
                "No internet connection!",
                Toast.LENGTH_SHORT
            ).show()

    }

    private fun updateUI(adapter: MoviesAdapter) {
        setUpRecycler(adapter)
        val data = viewModel.fetchLatestMovies(isConnected)

        data?.networkState?.observeChange(viewLifecycleOwner) {
            when (it) {
                is NetworkState.ERROR -> {
                    Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
                    binding.progress.hide()
                }
                NetworkState.HAS_LOADED -> {
                    binding.progress.hide()
                }
                NetworkState.IS_LOADING -> {
                    binding.progress.show()
                }
            }
        }

        data?.pagedList?.observeChange(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setUpRecycler(adapter: MoviesAdapter) {
        binding.recycler.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recycler.adapter = adapter
    }

    private fun setUpRefresher(adapter: MoviesAdapter) {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.refresh()
            updateUI(adapter)
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        checkInternetStatus()

        updateUI(adapter)
        setUpRefresher(adapter)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}