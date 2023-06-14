package com.braula.finnapp.domain.usecases

import com.braula.finnapp.domain.model.Ad
import com.braula.finnapp.domain.repository.AdService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadAdsUseCase @Inject constructor(
    private val service: AdService
) {
    suspend operator fun invoke(): Flow<Pair<List<Ad>, List<String>>> = flow {
        emit(service.getAds() to service.getFavoritesIds())
    }.distinctUntilChanged()
}