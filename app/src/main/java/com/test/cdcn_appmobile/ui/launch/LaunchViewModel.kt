package com.test.cdcn_appmobile.ui.launch

import androidx.lifecycle.ViewModel
import com.test.cdcn_appmobile.data.models.User
import com.test.cdcn_appmobile.data.repository.UserRepository
import com.test.test_by_anh_phu.bai1.extension.EMAIL_ADDRESS
import com.test.test_by_anh_phu.bai1.extension.PASSWORD

/*
 * Created by tuyenpc on 10/9/2022
 */

class LaunchViewModel(private val userRepository: UserRepository) : ViewModel() {

    companion object {

    }

    fun loginUser(
        email: String,
        pass: String,
        onStart: () -> Unit,
        onResult: () -> Unit,
    ) {

    }

    fun insertUser(users: User, onStart: () -> Unit, onResult: (done: Boolean) -> Unit) {

    }

    fun validEmail(mail: String): String = if (EMAIL_ADDRESS.matches(mail)) "" else "Email is Valid"

    fun validPassWord(pass: String): String =
        if (PASSWORD.matches(pass)) "" else "Wrong password format"
}
