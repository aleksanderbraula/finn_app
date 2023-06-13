package com.braula.finnapp.di

import com.braula.finnapp.domain.repository.AdsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /*@Provides
    @Singleton
    fun providesAdsService(): AdsService()*/

}