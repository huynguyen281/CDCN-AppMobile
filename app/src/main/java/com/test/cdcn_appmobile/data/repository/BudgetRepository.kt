package com.test.cdcn_appmobile.data.repository

import com.test.cdcn_appmobile.data.models.Budget
import com.test.cdcn_appmobile.data.models.ResponseRetrofit
import com.test.cdcn_appmobile.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

object BudgetRepository {
    suspend fun getBudget(authToken: String, idUser: String): Flow<ResponseRetrofit<out Budget?>> {
        return flow {
            val res = Constant.getRetrofit().getBudget(authToken, idUser)
            when (res.code()) {
                200 -> {
                    emit(res.body() ?: ResponseRetrofit(false,
                        "Lỗi khi thực hiện thao tác !!!",
                        null))
                }
                401 -> {
                    emit(ResponseRetrofit(false, "Vui lòng đăng nhập", null))
                }
                else -> {
                    emit(ResponseRetrofit(false, "Lỗi Server !!!", null))
                }
            }
        }.catch {
            emit(ResponseRetrofit(false, "Lỗi Khi thực hiện thao tác !!!", null))
        }
    }

    suspend fun updateBudget(
        authToken: String,
        idUser: String,
        limitMoney: String,
        unitTime: Int,
    ): Flow<ResponseRetrofit<out Budget?>> {
        return flow {
            val options: MutableMap<String, Long> = HashMap()
            options["limitMoney"] = limitMoney.toLong()
            options["unitTime"] = unitTime.toLong()
            val res = Constant.getRetrofit().updateBudget(authToken, idUser, options)
            res
            when (res.code()) {
                200 -> {
                    emit(res.body() ?: ResponseRetrofit(false,
                        "Lỗi khi thực hiện thao tác !!!",
                        null))
                }
                401 -> {
                    emit(ResponseRetrofit(false, "Vui lòng đăng nhập !!!", null))
                }
                else -> {
                    emit(ResponseRetrofit(false, "Lỗi Server !!!", null))
                }
            }
        }.catch {
            emit(ResponseRetrofit(false, "Lỗi Khi thực hiện thao tác !!!", null))
        }
    }
}
