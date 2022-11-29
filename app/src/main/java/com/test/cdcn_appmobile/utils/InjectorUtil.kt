package com.test.cdcn_appmobile.utils

import com.test.cdcn_appmobile.data.repository.UserRepository
import com.test.cdcn_appmobile.ui.launch.LaunchViewModelFactory

/*
 * Created by tuyen.dang on 10/10/2022
 */

object InjectorUtil {

    fun provideUserViewModelFactory(): LaunchViewModelFactory {
        val userRepository = UserRepository
        return LaunchViewModelFactory(userRepository)
    }
}
