package com.braula.finnapp.domain.usecases

import com.braula.finnapp.domain.repository.AdService
import javax.inject.Inject

class RemoveAdFromFavoritesUseCase  @Inject constructor(
    private val service: AdService
) {
    suspend operator fun invoke(id: String) {
        service.removeAdFromFavorites(id)
    }
}