package com.braula.finnapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.braula.finnapp.databinding.FragmentMainBinding
import com.braula.finnapp.ui.adapter.AdAdapter

class MainFragment: Fragment() {
    private lateinit var binding: FragmentMainBinding

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
    }

    private fun setupListView() {
        adAdapter = AdAdapter()

        binding.adListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adAdapter
        }
    }
}