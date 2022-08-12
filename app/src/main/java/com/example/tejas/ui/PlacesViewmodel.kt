package com.example.tejas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tejas.Constants.PLACE_OPEN_FROM
import com.example.tejas.extensions.removeMonthAndDate
import com.example.tejas.models.entities.place.MBTIEntityTypes.Companion.PLACE
import com.example.tejas.network.APIConstant.LIMIT
import com.example.tejas.network.APIConstant.OFFSET
import com.example.tejas.network.Resource
import com.example.tejas.repository.PlaceRepo
import com.example.tejas.responses.PlacesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewmodel @Inject constructor(
    private val searchPlaceRepo: PlaceRepo,
):ViewModel(){


    private val _placeResponse = MutableLiveData<Resource<PlacesResponse>>()
    val placeResponse: LiveData<Resource<PlacesResponse>> = _placeResponse

    /**
     * We need only those places who can satisfied these conditions.
     * 1-LifeSpan & Begin date is not null
     * 2-Begin date is after PLACE_OPEN_FROM i.e 1990
     * 3-Coordinates (Latitude & Longitude) is not Null
     */
    fun search(query: String) {
        viewModelScope.launch {
            _placeResponse.value = Resource.Loading
            val placeResponse: Resource<PlacesResponse> =
                searchPlaceRepo.searchEntity(PLACE, query, LIMIT, OFFSET)

            if (placeResponse is Resource.Success) {
                val places = placeResponse.value.places?.toMutableList()
                places?.filter { place ->
                    place.lifeSpan?.begin != null
                            &&
                            place.lifeSpan.begin.isNotEmpty()
                            &&
                            place.lifeSpan.begin.removeMonthAndDate()
                                .toInt() > PLACE_OPEN_FROM
                            &&
                            !place.coordinates?.latitude.isNullOrBlank()
                            &&
                            !place.coordinates?.longitude.isNullOrBlank()
                }?.let {
                    placeResponse.value.places.clear()
                    placeResponse.value.places.addAll(it)
                } ?: placeResponse.value.places?.clear()

            }
            _placeResponse.value = placeResponse
        }


    }


}