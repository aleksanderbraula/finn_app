package com.braula.finnapp.data.database.mappers

import com.braula.finnapp.data.database.entity.AdEntity
import com.braula.finnapp.domain.model.Ad

fun AdEntity.mapFromEntity(): Ad =
    Ad(
        id = this.id,
        imageSuffix = this.imageSuffix,
        title = this.title,
        price = this.price,
        location = this.location
    )


fun Ad.mapToEntity(): AdEntity =
    AdEntity(
        id = this.id,
        imageSuffix = this.imageSuffix,
        title = this.title,
        price = this.price,
        location = this.location,
        isFavorite = this.isFavorite
    )