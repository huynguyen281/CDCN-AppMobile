package com.test.cdcn_appmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.cdcn_appmobile.extension.addFragment
import com.test.cdcn_appmobile.ui.launch.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        addFragment(R.id.ctReplaceFragment, LoginFragment())
    }
}