package com.braula.finnapp.data.database.service

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.braula.finnapp.data.database.dao.AdDao
import com.braula.finnapp.data.database.entity.AdEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AdDatabaseServiceTest {

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockAdDao: AdDao
    private lateinit var adDatabaseService: AdDatabaseService

    @Before
    fun setUp() {
        mockAdDao = mock(AdDao::class.java)
        adDatabaseService = AdDatabaseService(mockAdDao)
    }

    @Test
    fun `should getAllAds() return empty list if no items in database`() = runBlocking {
        //given
        `when`(mockAdDao.getAll()).thenReturn(listOf())

        //when
        val ads = adDatabaseService.getAllAds()

        //then
        Assert.assertTrue(ads.isEmpty())
    }

    @Test
    fun `should getAllAds() return list of ads no items in database`() = runBlocking {
        //given
        val ad1 = AdEntity("1", "url1", "title1", 1, "location1", false)
        val ad2 = AdEntity("2", "url2", "title2", 2, "location2", false)
        `when`(mockAdDao.getAll()).thenReturn(listOf(ad1, ad2))

        //when
        val ads = adDatabaseService.getAllAds()

        //then
        Assert.assertEquals(2, ads.size)
        Assert.assertEquals("1", ads.first().id)
        Assert.assertEquals("title1", ads.first().title)
        Assert.assertEquals(1, ads.first().price)
        Assert.assertEquals("location1", ads.first().location)
    }
}