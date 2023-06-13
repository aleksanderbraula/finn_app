package com.braula.finnapp.data.remote.repository

import com.braula.finnapp.data.remote.dto.AdsResponse
import retrofit2.http.GET

interface AdsRemoteRepository {
    @GET("baldermork/6a1bcc8f429dcdb8f9196e917e5138bd/raw/discover.json")
    suspend fun getAds(): AdsResponse
}
