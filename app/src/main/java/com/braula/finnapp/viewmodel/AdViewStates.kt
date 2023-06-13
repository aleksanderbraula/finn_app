package com.braula.finnapp.viewmodel

import com.braula.finnapp.domain.model.Ad

data class AdViewState(
    val isLoading: Boolean = false,
    val adList: List<Ad> = arrayListOf()
)