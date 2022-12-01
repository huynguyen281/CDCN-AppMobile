package com.test.cdcn_appmobile.extension

/*
 * Created by tuyen.dang on 12/1/2022
 */

internal fun String.toDayMonth(): String {
    return "${if (this.length == 1) "0" else ""}$this"
}