package com.test.cdcn_appmobile.utils

import com.test.cdcn_appmobile.data.repository.BudgetRepository
import com.test.cdcn_appmobile.data.repository.UserRepository
import com.test.cdcn_appmobile.ui.launch.LaunchViewModelFactory
import com.test.cdcn_appmobile.ui.main.budget.BudgetViewModelFactory

/*
 * Created by tuyen.dang on 10/10/2022
 */

object InjectorUtil {

    fun provideLaunchViewModelFactory(): LaunchViewModelFactory {
        return LaunchViewModelFactory(UserRepository)
    }

    fun provideBudgetViewModelFactory(): BudgetViewModelFactory {
        return BudgetViewModelFactory(BudgetRepository)
    }
}
