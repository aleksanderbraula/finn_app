package com.braula.finnapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.braula.finnapp.domain.model.Ad

@Entity(tableName = "ads")
data class AdEntity(
    @PrimaryKey var id: String,
    val imageSuffix: String?,
    val title: String?,
    val price: Int?,
    val location: String?,
    var isFavorite: Boolean?
)