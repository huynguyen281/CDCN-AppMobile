package com.test.cdcn_appmobile.data.models

import com.google.gson.annotations.SerializedName

data class Expenditure(
    @SerializedName("id") val id: String,
    @SerializedName("cost") val cost: Long,
    @SerializedName("date") val date: String,
    @SerializedName("imageIcon") val imageIcon: String,
    @SerializedName("categoryName") val categoryName: String,
    @SerializedName("categoryId") val categoryId: String,
    @SerializedName("categoryType") val categoryType: Int,
    @SerializedName("categoryTypeName") val categoryTypeName: String,
    @SerializedName("note") val note: String
)
