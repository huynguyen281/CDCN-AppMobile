package com.test.cdcn_appmobile.data.repository

import com.test.cdcn_appmobile.data.models.Budget
import com.test.cdcn_appmobile.data.models.ResponseRetrofit
import com.test.cdcn_appmobile.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object BudgetRepository {
    suspend fun getBudget(authToken: String, idUser: String): Flow<ResponseRetrofit<Budget?>> {
        return flow {
            try {
                val res = Constant.getRetrofit().getBudget(authToken, idUser)
                when (res.code()) {
                    200 -> {
                        emit(res.body() ?: ResponseRetrofit(false, "Lỗi khi thực hiện thao tác !!!", null))
                    }
                    401 -> {
                        emit(ResponseRetrofit(false, "Vui lòng đăng nhập", null))
                    }
                    else -> {
                        emit(ResponseRetrofit(false, "Lỗi Server !!!", null))
                    }
                }
            } catch (e: Exception) {

            }
        }
    }

    suspend fun updateBudget(
        authToken: String,
        idUser: String,
        limitMoney: String,
        unitTime: Int
    ): Flow<ResponseRetrofit<Budget?>> {
        return flow {
            try {
                val options: MutableMap<String, String> = HashMap()
                options["limitMoney"] = limitMoney
                options["unitTime"] = unitTime.toString()
                val res = Constant.getRetrofit().updateBudget(authToken, idUser, options)
                when (res.code()) {
                    200 -> {
                        emit(res.body() ?: ResponseRetrofit(false, "Lỗi khi thực hiện thao tác !!!", null))
                    }
                    401 -> {
                        emit(ResponseRetrofit(false, "Vui lòng đăng nhập !!!", null))
                    }
                    else -> {
                        emit(ResponseRetrofit(false, "Lỗi Server !!!", null))
                    }
                }
            } catch (e: Exception) {

            }
        }
    }
}
