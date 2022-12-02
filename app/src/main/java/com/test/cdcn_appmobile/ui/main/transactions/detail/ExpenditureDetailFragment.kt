package com.test.cdcn_appmobile.ui.main.transactions.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.cdcn_appmobile.databinding.FragmentDetailExpenditureBinding

/*
 * Created by tuyen.dang on 12/2/2022
 */

class ExpenditureDetailFragment : Fragment() {

    private var binding: FragmentDetailExpenditureBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailExpenditureBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {

    }

    private fun initListener() {

    }
}