package com.test.cdcn_appmobile.data.repository

import com.test.cdcn_appmobile.data.models.Expenditure
import com.test.cdcn_appmobile.data.models.ResponseRetrofit
import com.test.cdcn_appmobile.extension.flowWithCatch
import com.test.cdcn_appmobile.utils.Constant
import kotlinx.coroutines.flow.Flow

object ExpenditureRepository {
    internal suspend fun getExpenditure(
        authToken: String,
        idUser: String,
        date: String
    ): Flow<ResponseRetrofit<out ArrayList<Expenditure>?>> {
        val options: MutableMap<String, String> = HashMap()
        options["date"] = date
        options["id"] = idUser
        val res = Constant.getRetrofit().getExpenditure(authToken, options)
        return flowWithCatch(res)
    }
}