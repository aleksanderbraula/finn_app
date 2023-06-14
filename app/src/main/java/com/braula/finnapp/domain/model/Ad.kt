package com.braula.finnapp.domain.model

import com.braula.finnapp.data.database.entity.AdEntity

data class Ad(
    val id: String,
    val imageSuffix: String?,
    val title: String?,
    val price: Int?,
    val location: String?,
    var isFavorite: Boolean = false
) {
    fun areContentsTheSame(): String  {
        return id + imageSuffix + title + price + location
    }


}

fun Ad.mapToEntity(): AdEntity =
    AdEntity(
        id = this.id,
        imageSuffix = this.imageSuffix,
        title = this.title,
        price = this.price,
        location = this.location,
        isFavorite = this.isFavorite
    )

