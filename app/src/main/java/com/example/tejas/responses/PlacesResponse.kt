package com.example.tejas.responses

import com.example.tejas.models.entities.place.Place
import com.google.gson.annotations.SerializedName

data class PlacesResponse(
  @SerializedName("count")
  val count: Int?,

  @SerializedName("places")
  val places: ArrayList<Place>?
 )