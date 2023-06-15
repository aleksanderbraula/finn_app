package com.braula.finnapp.data.remote.repository

import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class AdRemoteRepositoryTest {

    private val mockWebServer: MockWebServer = MockWebServer()

    private lateinit var adRemoteRepository: AdRemoteRepository

    private val responseJsonString = "{\"items\": [{\"description\": \"Gammelt tømmerhus\",\"id\": \"128186522\",\"url\": \"/128186522\",\"ad-type\": \"BAP\",\"location\": \"Engerdal\",\"type\": \"AD\",\"price\": {\"value\": 10000},\"image\": {\"url\": \"2018/9/vertical-5/01/2/128/186/522_683367637.jpg\",\"height\": 1067,\"width\": 1600,\"type\": \"GENERAL\",\"scalable\": true},\"score\": 0.19820467,\"version\": \"mul-pop-thompzon\",\"favourite\": {\"itemId\": \"128186522\",\"itemType\": \"Ad\"}}]}"

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockWebServer.start()

        val okHttpClient = OkHttpClient().newBuilder()
            .build()

        val retrofit =
            Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        adRemoteRepository = retrofit.create(AdRemoteRepository::class.java)
    }

    @Test
    fun `should getAds() return ads if successful response`() = runBlocking {
        //given
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(responseJsonString))

        //when
        val ads = adRemoteRepository.getAds()

        //then
        Assert.assertEquals(1, ads.items.size)
        Assert.assertEquals("128186522", ads.items.first().id)
        Assert.assertEquals("Gammelt tømmerhus", ads.items.first().description)
        Assert.assertEquals(10000, ads.items.first().price?.value)
        Assert.assertEquals("Engerdal", ads.items.first().location)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}