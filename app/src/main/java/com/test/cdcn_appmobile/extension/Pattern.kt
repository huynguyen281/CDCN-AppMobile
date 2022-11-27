package com.test.test_by_anh_phu.bai1.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

internal val EMAIL_ADDRESS: Regex by lazy { "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,18}".toRegex() }

internal val PASSWORD: Regex by lazy { "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\$@\$!#%*?&.,])[A-Za-z\\d\$@\$!#%*?&.,]{8,}".toRegex() }

@SuppressLint("SimpleDateFormat")
fun getDay(): String {
    val sdf = SimpleDateFormat("HH:mm · MM/dd/yy · ")
    return sdf.format(Date())
}

@SuppressLint("SimpleDateFormat")
val patternTime = SimpleDateFormat("mm:ss")
