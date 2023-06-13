package com.braula.finnapp.domain.usecases

import com.braula.finnapp.domain.model.Ad
import com.braula.finnapp.domain.repository.AdsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadAdsUseCase @Inject constructor(
    private val service: AdsService
) {
    suspend operator fun invoke(): Flow<List<Ad>> = flow {
        emit(service.getAds())
    }.distinctUntilChanged()
}