package com.braula.finnapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.braula.finnapp.data.database.dao.AdDao
import com.braula.finnapp.data.database.entity.AdEntity

object DbHelper {
    const val DB_VERSION = 1
    const val DB_NAME = "finn_db"
}
@Database(
    entities = [
        AdEntity::class
    ],
    version = DbHelper.DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private lateinit var instance: AppDatabase
        fun getDbInstance(context: Context): AppDatabase {
            return if (!this::instance.isInitialized) {
                synchronized(AppDatabase::class) {
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DbHelper.DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            } else {
                instance
            }
        }
    }

    abstract fun adDao(): AdDao
}