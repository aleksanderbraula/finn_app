package com.braula.finnapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.braula.finnapp.data.database.entity.AdEntity

@Dao
interface AdDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ad: AdEntity)

    @Query("DELETE FROM ads WHERE `id` = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT id FROM ads")
    suspend fun getIds(): List<String>
}
