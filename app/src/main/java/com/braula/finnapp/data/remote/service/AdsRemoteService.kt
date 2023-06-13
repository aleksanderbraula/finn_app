package com.braula.finnapp.data.remote.service

import com.braula.finnapp.data.remote.dto.mapFromRemote
import com.braula.finnapp.data.remote.repository.AdsRemoteRepository
import com.braula.finnapp.domain.model.Ad
import javax.inject.Inject

class AdsRemoteService @Inject constructor(
    private val adsRemoteRepository: AdsRemoteRepository
) {
    suspend fun getAds(): List<Ad> = adsRemoteRepository.getAds().mapFromRemote()
}
