package com.hazem.boruto.data.remote

import com.hazem.boruto.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {
    @GET("/boruto/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/boruto/heroes/search")
    suspend fun searchForHeroes(
        @Query("name") name:String
    ): ApiResponse
}