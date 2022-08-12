package com.example.tejas.network

import com.example.tejas.responses.PlacesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MB_API {


    @GET("{entity}/")
    suspend fun searchEntity(@Path("entity") entity: String?,
                             @Query("query") searchTerm: String?,
                             @Query("limit") limit: Int,
                             @Query("offset") offset: Int): PlacesResponse
}