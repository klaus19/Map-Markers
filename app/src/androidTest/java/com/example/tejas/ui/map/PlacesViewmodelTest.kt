package com.example.tejas.ui.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tejas.network.MBClient
import com.example.tejas.network.MB_API
import com.example.tejas.network.Resource
import com.example.tejas.repository.PlaceRepo
import com.example.tejas.responses.PlacesResponse
import com.example.tejas.ui.PlacesViewmodel
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlacesViewmodelTest {

    //TODO Will inject all these properties via hilt
    private lateinit var viewModel: PlacesViewmodel
    private lateinit var placesRepo: PlaceRepo
    private lateinit var mbApi: MB_API

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        mbApi = MBClient().buildApi(MB_API::class.java)
        placesRepo = PlaceRepo(mbApi)
        viewModel = PlacesViewmodel(placesRepo)
    }

    @Test
    fun testPlacesViewModel() {
        /**
         * To test the viewmodel via mock data source
         */
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val jsonString = loadJSONFromAsset(context, "place_response.json")
//        val placeResponse: PlaceResponse = Gson().fromJson(jsonString, PlaceResponse::class.java)
//        viewModel.placeResponse.value = Resource.Success(placeResponse)


        viewModel.search("Eiffel Tower")
        val placeResource: Resource<PlacesResponse> = viewModel.placeResponse.getOrAwaitValue()

        if (placeResource is Resource.Success) {
            val result = placeResource.value.places != null

            placeResource.value.places?.forEach {
                Truth.assertThat(it.lifeSpan != null).isTrue()
            }

            Truth.assertThat(result).isTrue()

        }

    }
}