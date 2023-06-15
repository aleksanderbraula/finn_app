package com.braula.finnapp.data.database.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.braula.finnapp.data.database.AppDatabase
import com.braula.finnapp.data.database.entity.AdEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AdDaoTest {

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var testAdminDatabase: AppDatabase
    private lateinit var adDao: AdDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        testAdminDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries().build()
        adDao = testAdminDatabase.adDao()
    }

    @Test
    fun `should getAll() return empty list if no items in database`() = runBlocking {
        //when
        val databaseEntities = adDao.getAll()

        //then
        Assert.assertTrue(databaseEntities.isEmpty())
    }

    @Test
    fun `should getAll() return list of ads if items present in database`() = runBlocking {
        //given
        val ad1 = AdEntity("1", "url1", "title1", 1, "location1", false)
        val ad2 = AdEntity("2", "url2", "title2", 2, "location2", false)
        listOf(ad1, ad2).forEach {
            adDao.insert(it)
        }

        //when
        val databaseEntities = adDao.getAll()

        //then
        Assert.assertEquals(2, databaseEntities.size)
        Assert.assertTrue(databaseEntities.contains(ad1))
        Assert.assertTrue(databaseEntities.contains(ad2))
    }

    @Test
    fun `should getIds() return empty list if no items in database`() = runBlocking {
        //when
        val databaseEntities = adDao.getIds()

        //then
        Assert.assertTrue(databaseEntities.isEmpty())
    }

    @Test
    fun `should getIds() return list of ids if items present in database`() = runBlocking {
        //given
        val ad1 = AdEntity("1", "url1", "title1", 1, "location1", false)
        val ad2 = AdEntity("2", "url2", "title2", 2, "location2", false)
        listOf(ad1, ad2).forEach {
            adDao.insert(it)
        }

        //when
        val databaseEntities = adDao.getIds()

        //then
        Assert.assertEquals(2, databaseEntities.size)
        Assert.assertTrue(databaseEntities.contains("1"))
        Assert.assertTrue(databaseEntities.contains("2"))
    }

    @Test
    fun `should insert() should add item to databas`() = runBlocking {
        //given
        val ad1 = AdEntity("1", "url1", "title1", 1, "location1", false)

        //when
        val databaseEntitiesBefore = adDao.getAll()
        adDao.insert(ad1)
        val databaseEntitiesAfter = adDao.getAll()

        //then
        Assert.assertTrue(databaseEntitiesBefore.isEmpty())
        Assert.assertEquals(1, databaseEntitiesAfter.size)
    }

    @Test
    fun `should delete() remove item from database`() = runBlocking {
        //given
        val ad1 = AdEntity("1", "url1", "title1", 1, "location1", false)
        val ad2 = AdEntity("2", "url2", "title2", 2, "location2", false)
        listOf(ad1, ad2).forEach {
            adDao.insert(it)
        }
        //when
        val databaseEntitiesBefore = adDao.getAll()
        adDao.deleteById(ad1.id)
        val databaseEntitiesAfter = adDao.getAll()

        //then
        //then
        Assert.assertEquals(2, databaseEntitiesBefore.size)
        Assert.assertEquals(1, databaseEntitiesAfter.size)
        Assert.assertFalse(databaseEntitiesAfter.contains(ad1))
        Assert.assertTrue(databaseEntitiesAfter.contains(ad2))
    }
}