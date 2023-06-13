package com.braula.finnapp.data.remote.dto

import com.braula.finnapp.domain.model.Ad
import com.google.gson.annotations.SerializedName

data class AdsResponse(
    @SerializedName("items")
    val items: List<AdDto>
)

data class AdDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("price")
    val price: PriceDto?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("image")
    val image: ImageDto?
)

data class PriceDto(
    @SerializedName("value")
    val value: Int
)

data class ImageDto(
    @SerializedName("url")
    val url: String
)

fun AdsResponse.mapFromRemote(): List<Ad> {
    val ads = arrayListOf<Ad>()

    this.items.forEach {
        ads.add(
            Ad(
                id = it.id,
                imageSuffix = it.image?.url,
                title = it.description,
                price = it.price?.value ?: 0,
                location = it.location
            )
        )
    }

    return ads
}