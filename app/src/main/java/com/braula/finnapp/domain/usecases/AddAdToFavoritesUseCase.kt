package com.braula.finnapp.domain.usecases

import com.braula.finnapp.domain.model.Ad
import com.braula.finnapp.domain.repository.AdService
import javax.inject.Inject

class AddAdToFavoritesUseCase @Inject constructor(
    private val service: AdService
) {
    suspend operator fun invoke(ad: Ad) {
        service.addAdToFavorites(ad)
    }
}