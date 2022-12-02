package com.test.cdcn_appmobile.extension

import com.test.cdcn_appmobile.data.models.ResponseRetrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

internal fun <T> flowWithCatch(
    res: Response<ResponseRetrofit<T?>>
): Flow<ResponseRetrofit<out T?>> {
    return flow {
        when (res.code()) {
            200 -> {
                emit(
                    res.body() ?: ResponseRetrofit(
                        false,
                        "Lỗi khi thực hiện thao tác !!!",
                        null
                    )
                )
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