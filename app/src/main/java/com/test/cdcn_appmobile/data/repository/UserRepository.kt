package com.test.cdcn_appmobile.data.repository

import com.test.cdcn_appmobile.data.models.ResponseRetrofit
import com.test.cdcn_appmobile.data.models.User
import com.test.cdcn_appmobile.extension.flowWithCatch
import com.test.cdcn_appmobile.utils.Constant
import kotlinx.coroutines.flow.Flow


/*
 * Created by tuyen.dang on 11/27/2022
 */

object UserRepository {
    suspend fun login(email: String, pass: String): Flow<ResponseRetrofit<out User?>> {
        val options: MutableMap<String, String> = HashMap()
        options["userName"] = email
        options["password"] = pass
        val res = Constant.getRetrofit().login(options)
        return flowWithCatch(res)
    }

    suspend fun register(
        email: String,
        pass: String,
        name: String,
    ): Flow<ResponseRetrofit<out Any?>> {
        val options: MutableMap<String, String> = HashMap()
        options["userName"] = email
        options["email"] = email
        options["password"] = pass
        options["name"] = name
        val res = Constant.getRetrofit().register(options)
        return flowWithCatch(res)
    }
}
