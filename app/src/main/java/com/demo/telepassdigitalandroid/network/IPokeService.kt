package com.demo.telepassdigitalandroid.network

import com.demo.telepassdigitalandroid.network.model.PokeDetail
import com.demo.telepassdigitalandroid.network.model.PokeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IPokeService {

    @GET("pokemon")
    suspend fun getList(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<PokeList>

    @GET
    suspend fun getDetail(@Url url: String): Response<PokeDetail>

}