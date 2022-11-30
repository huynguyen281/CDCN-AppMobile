package com.test.cdcn_appmobile.data.service

import com.test.cdcn_appmobile.data.models.Budget
import com.test.cdcn_appmobile.data.models.ResponseRetrofit
import com.test.cdcn_appmobile.data.models.User
import retrofit2.Response
import retrofit2.http.*

/*
 * Created by tuyen.dang on 11/27/2022
 */

interface ApiService {
    @POST("/api/Account/login")
    suspend fun login(@Body options: Map<String, String>): Response<ResponseRetrofit<User?>>


    @POST("/api/Account/register")
    suspend fun register(@Body options: Map<String, String>): Response<ResponseRetrofit<Any?>>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json; charset=utf-8",
    )
    @GET("/api/Budget/{id}")
    suspend fun getBudget(
        @Header("authorization") token: String,
        @Path("id") idUser: String
    ): Response<ResponseRetrofit<Budget?>>
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json; charset=utf-8",
    )
    @PUT("/api/Budget/update/{id}")
    suspend fun updateBudget(
        @Header("authorization") token: String,
        @Path("id") idUser: String,
        @Body options: Map<String, String>
    ): Response<ResponseRetrofit<Budget?>>
}