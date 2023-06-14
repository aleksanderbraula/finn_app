package com.braula.finnapp.data.database.service

import com.braula.finnapp.data.database.dao.AdDao
import com.braula.finnapp.data.database.mappers.mapFromEntity
import com.braula.finnapp.data.database.mappers.mapToEntity
import com.braula.finnapp.domain.model.Ad
import javax.inject.Inject

class AdDatabaseService @Inject constructor(
    private val adDao: AdDao
) {
    suspend fun addAdToFavorites(ad: Ad) {
        adDao.insert(ad.mapToEntity())
    }

    suspend fun removeAdFromFavorites(id: String) {
        adDao.deleteById(id)
    }

    suspend fun getFavoritesIds(): List<String> {
        return adDao.getIds()
    }

    suspend fun getAllAds(): List<Ad> {
        return adDao.getAll().map { it.mapFromEntity() }
    }
}
