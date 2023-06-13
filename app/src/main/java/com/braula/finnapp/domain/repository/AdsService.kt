package com.braula.finnapp.domain.repository

import com.braula.finnapp.data.remote.service.AdsRemoteService
import com.braula.finnapp.di.IoDispatcher
import com.braula.finnapp.domain.model.Ad
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdsService @Inject constructor(
    private val adsService: AdsRemoteService,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getAds(): List<Ad> =
        withContext(ioDispatcher) {
            adsService.getAds()
        }
}