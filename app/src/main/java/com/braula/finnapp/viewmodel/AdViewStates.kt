package com.braula.finnapp.viewmodel

import com.braula.finnapp.domain.model.Ad

data class AdViewState(
    val isLoading: Boolean = false,
    val ads: List<Ad> = arrayListOf(),
    val favoriteIds: List<String> = arrayListOf()
)