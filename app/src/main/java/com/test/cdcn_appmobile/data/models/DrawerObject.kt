package com.test.cdcn_appmobile.data.models

import com.google.gson.annotations.SerializedName

data class DrawerObject(
    @SerializedName("spendMoney") val spendMoney: Long,
    @SerializedName("receiveMoney") val receivedMoney: Long,
    @SerializedName("time") val time: String
)
