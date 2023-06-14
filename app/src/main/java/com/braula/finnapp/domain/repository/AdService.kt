package com.braula.finnapp.domain.repository

import android.content.Context
import com.braula.finnapp.data.database.service.AdDatabaseService
import com.braula.finnapp.data.remote.service.AdRemoteService
import com.braula.finnapp.di.IoDispatcher
import com.braula.finnapp.domain.model.Ad
import com.braula.finnapp.utils.isNetworkAvailable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdService @Inject constructor(
    private val adRemoteService: AdRemoteService,
    private val adDatabaseService: AdDatabaseService,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val context: Context
) {
    suspend fun getAds(): List<Ad> {
        return withContext(ioDispatcher) {
            if (context.isNetworkAvailable()) {
                adRemoteService.getAds()
            } else {
                adDatabaseService.getAllAds()
            }
        }
    }

    suspend fun getFavoritesIds(): List<String> =
        withContext(ioDispatcher) {
            adDatabaseService.getFavoritesIds()
        }

    suspend fun addAdToFavorites(ad: Ad) {
        withContext(ioDispatcher) {
            adDatabaseService.addAdToFavorites(ad)
        }
    }

    suspend fun removeAdFromFavorites(id: String) {
        withContext(ioDispatcher) {
            adDatabaseService.removeAdFromFavorites(id)
        }
    }
}