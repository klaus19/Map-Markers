package com.example.tejas.network

import com.example.tejas.models.entities.place.MBTIEntityTypes.Companion.PLACE
import com.example.tejas.network.APIConstant.LIMIT
import com.example.tejas.network.APIConstant.OFFSET
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MBClientTest {

    private val mbApi: MB_API = MBClient().buildApi(MB_API::class.java)

    @Test
    fun `GET Places`() {
        runBlocking {
            val placesResponse = mbApi.searchEntity(PLACE, "Kaunas", LIMIT, OFFSET)
            Assert.assertNotNull(placesResponse).apply {

            }
        }
    }
}