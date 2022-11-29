package com.test.cdcn_appmobile.data.repository

import com.test.cdcn_appmobile.data.models.ResponseRetrofit
import com.test.cdcn_appmobile.data.models.User
import com.test.cdcn_appmobile.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await


/*
 * Created by tuyen.dang on 11/27/2022
 */

object UserRepository {
    suspend fun login(email: String, pass: String): Flow<ResponseRetrofit<User>> {
        return flow {
            val options: MutableMap<String, String> = HashMap()
            options["userName"] = email
            options["password"] = pass
            val res = Constant.getRetrofit().login(options)
            emit(res.await())
        }
    }

    suspend fun register(email: String, pass: String, name: String): Flow<ResponseRetrofit<User>> {
        return flow {
            val options: MutableMap<String, String> = HashMap()
            options["userName"] = email
            options["email"] = email
            options["password"] = pass
            options["name"] = name
            val res = Constant.getRetrofit().register(options)
            emit(res.await())
        }
    }
}