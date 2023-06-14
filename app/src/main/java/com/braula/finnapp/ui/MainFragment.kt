package com.braula.finnapp.ui

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.braula.finnapp.R
import com.braula.finnapp.databinding.FragmentMainBinding
import com.braula.finnapp.domain.model.Ad
import com.braula.finnapp.ui.adapter.AdAdapter
import com.braula.finnapp.utils.isNetworkAvailable
import com.braula.finnapp.utils.visibility
import com.braula.finnapp.viewmodel.AdViewModel
import com.braula.finnapp.viewmodel.AdViewState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment: Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: AdViewModel by viewModels()

    private lateinit var adAdapter: AdAdapter

    private val favoriteCallback = object : AdAdapter.FavoriteCallback {
        override fun onFavoriteAdded(ad: Ad) {
            viewModel.addAdToFavorites(ad)
        }

        override fun onFavoriteRemoved(id: String) {
            viewModel.removeAdFromFavorites(id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListView()
        initObservers()
        loadData()
    }

    private fun loadData() {
        val hasInternet = requireContext().isNetworkAvailable()
        if (!hasInternet) {
            showNetworkWarning()
        }
        viewModel.loadAds()
    }

    private fun setupView() {
        binding.favoritesToggle.setOnCheckedChangeListener { _, state ->
            if (state) {
                adAdapter.showOnlyFavorites()
            } else {
                adAdapter.showAll()
            }
        }
    }

    private fun setupListView() {
        adAdapter = AdAdapter(favoriteCallback)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = adAdapter
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.adViewState().collect {
                    handleViewState(it)
                }
            }
        }
    }

    private fun handleViewState(uiViewState: AdViewState) {
        with(binding) {
            loadingView visibility uiViewState.isLoading

            if (!uiViewState.isLoading) {
                val hasAds = uiViewState.ads.isEmpty()
                emptyText visibility hasAds
                recyclerView visibility !hasAds

                adAdapter.submitFavoriteIds(uiViewState.favoriteIds)
                adAdapter.submitAds(uiViewState.ads)
            }
        }
    }

    private fun showNetworkWarning() {
        Snackbar
            .make(requireActivity().findViewById(android.R.id.content), R.string.no_internet, Snackbar.LENGTH_INDEFINITE)
            .setAction(resources.getString(R.string.try_again)) {
                loadData()
            }.show()
    }
}