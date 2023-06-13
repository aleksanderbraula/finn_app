package com.braula.finnapp.domain.model

data class Ad(
    val id: String,
    val imageSuffix: String?,
    val title: String?,
    val price: Int?,
    val location: String?,
    val isFavorite: Boolean = false
) {
    fun areContentsTheSame(): String  {
        return id + imageSuffix + title + price + location
    }
}
