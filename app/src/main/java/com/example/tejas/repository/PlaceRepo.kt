package com.example.tejas.repository

import com.example.tejas.network.MB_API
import com.example.tejas.network.SafeApicall
import javax.inject.Inject


class PlaceRepo @Inject constructor(
    private val mbApi: MB_API
):SafeApicall{

    suspend fun searchEntity(
        entity: String?,
        searchTerm: String?,
        limit: Int,
        offset: Int
    ) = safeApiCall { mbApi.searchEntity(entity, searchTerm, limit, offset) }


}