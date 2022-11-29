package com.test.cdcn_appmobile.data.service

import com.test.cdcn_appmobile.data.models.ResponseRetrofit
import com.test.cdcn_appmobile.data.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/*
 * Created by tuyen.dang on 11/27/2022
 */

interface ApiService {
    @POST("/api/Account/login")
    fun login(@Body options: Map<String, String>): Call<ResponseRetrofit<User>>


    @POST("/api/Account/register")
    fun register(@Body options: Map<String, String>): Call<ResponseRetrofit<User>>
}