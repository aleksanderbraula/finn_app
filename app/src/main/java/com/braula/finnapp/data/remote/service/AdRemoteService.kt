package com.braula.finnapp.data.remote.service

import com.braula.finnapp.data.remote.dto.mapFromRemote
import com.braula.finnapp.data.remote.repository.AdRemoteRepository
import com.braula.finnapp.domain.model.Ad
import javax.inject.Inject

class AdRemoteService @Inject constructor(
    private val adRemoteRepository: AdRemoteRepository
) {
    suspend fun getAds(): List<Ad> = adRemoteRepository.getAds().mapFromRemote()
}
