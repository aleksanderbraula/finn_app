package com.braula.finnapp.di

import android.content.Context
import com.braula.finnapp.data.database.AppDatabase
import com.braula.finnapp.data.database.dao.AdDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.getDbInstance(context)
    }

    @Singleton
    @Provides
    fun provideEquipmentDao(appDatabase: AppDatabase): AdDao {
        return appDatabase.adDao()
    }
}