package com.braula.finnapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.braula.finnapp.databinding.FragmentMainBinding
import com.braula.finnapp.ui.adapter.AdAdapter
import com.braula.finnapp.viewmodel.AdViewModel
import com.braula.finnapp.viewmodel.AdViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment: Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: AdViewModel by viewModels()

    private lateinit var adAdapter: AdAdapter

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
        setupListView()
        initObservers()
        viewModel.loadAds()
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
        if (uiViewState.isLoading) {
            showLoading()
        } else {
            hideLoading()
            adAdapter.submitList(uiViewState.adList)
        }
    }

    private fun showLoading() {
        binding.loadingView.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loadingView.visibility = View.GONE
    }

    private fun setupListView() {
        adAdapter = AdAdapter()

        binding.adListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adAdapter
        }
    }
}