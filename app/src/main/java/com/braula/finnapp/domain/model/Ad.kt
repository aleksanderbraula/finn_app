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

