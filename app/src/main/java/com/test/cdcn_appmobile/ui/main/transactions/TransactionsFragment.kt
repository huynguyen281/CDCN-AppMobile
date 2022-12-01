package com.test.cdcn_appmobile.ui.main.transactions

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.cdcn_appmobile.R
import com.test.cdcn_appmobile.data.models.Expenditure
import com.test.cdcn_appmobile.data.models.ItemChoice
import com.test.cdcn_appmobile.databinding.FragmentTransactionsBinding
import com.test.cdcn_appmobile.extension.OnItemChoice
import com.test.cdcn_appmobile.extension.setVisibility
import com.test.cdcn_appmobile.ui.dialog.ChoiceFragment
import com.test.cdcn_appmobile.ui.main.transactions.adapters.DayPickerAdapter
import com.test.cdcn_appmobile.ui.main.transactions.adapters.ExpenditureAdapter
import com.test.cdcn_appmobile.utils.Constant
import com.test.cdcn_appmobile.utils.InjectorUtil
import java.util.*

/*
 * Created by tuyen.dang on 11/27/2022
 */

class TransactionsFragment : Fragment() {

    private var binding: FragmentTransactionsBinding? = null
    private var transactionsViewModel: TransactionsViewModel? = null
    private var listDay: MutableList<ItemChoice> = ArrayList()
    private var listMonth: MutableList<ItemChoice> = ArrayList()
    private var listYear: MutableList<ItemChoice> = ArrayList()
    private var choiceFragment: ChoiceFragment? = null
    private var expenditureAdapter: ExpenditureAdapter? = null
    private var listExpenditure: MutableList<Expenditure> = ArrayList()
    private var dayPickerAdapter: DayPickerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initDataMonthYear()
        initDataDay(
            transactionsViewModel?.getIdMonthChosen()?.value ?: (Calendar.getInstance()
                .get(Calendar.MONTH) + 1),
            transactionsViewModel?.getIdYearChosen()?.value ?: Calendar.getInstance()
                .get(Calendar.YEAR)
        )
        initListener()
        if (transactionsViewModel?.getListExpenditure() == null) {
            loadData()
        }
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun initView() {

        transactionsViewModel =
            ViewModelProvider(
                requireActivity(),
                InjectorUtil.transactionsViewModelFactory()
            )[TransactionsViewModel::class.java]

        expenditureAdapter = ExpenditureAdapter(listExpenditure) {

        }

        dayPickerAdapter = DayPickerAdapter(listDay) {
            dayPickerAdapter?.indexSelected = it.id - 1
            dayPickerAdapter?.notifyDataSetChanged()
        }
        binding?.run {

            transactionsViewModel?.run {

                getIdYearChosen().observe(viewLifecycleOwner) {
                    btnChoiceYear.text = it.toString()
                    listYear.OnItemChoice(it)
                }

                getIdMonthChosen().observe(viewLifecycleOwner) {
                    btnChoiceMonth.text = it.toString()
                    listMonth.OnItemChoice(it)
                    initDataDay(
                        it,
                        getIdYearChosen().value ?: Calendar.getInstance().get(Calendar.YEAR)
                    )
                }

                getIdDayChosen().observe(viewLifecycleOwner) {
                    listDay.OnItemChoice(it)
                }

                getListExpenditure().observe(viewLifecycleOwner) {
                    it?.let {
                        listExpenditure.clear()
                        listExpenditure = it
                        expenditureAdapter?.notifyDataSetChanged()
                    }
                }

                setIdYearChosen(Calendar.getInstance().get(Calendar.YEAR))
                setIdMonthChosen(Calendar.getInstance().get(Calendar.MONTH) + 1)
                setIdDayChosen(Calendar.getInstance().get(Calendar.DATE))
            }

            rclViewSpending.layoutManager = LinearLayoutManager(context)
            rclViewSpending.adapter = expenditureAdapter

            rclViewDay.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rclViewDay.adapter = dayPickerAdapter
        }

    }

    private fun initListener() {
        binding?.run {
            btnChoiceYear.setOnClickListener {
                openDialogChoice("Năm", listYear) { idChosen ->
                    transactionsViewModel?.setIdYearChosen(idChosen)
                }
            }

            btnChoiceMonth.setOnClickListener {
                openDialogChoice("Tháng", listMonth) { idChosen ->
                    transactionsViewModel?.setIdMonthChosen(idChosen)
                }
            }

            refreshLayout.setOnRefreshListener {
                loadData()
            }
        }
    }

    private fun initDataMonthYear() {
        listMonth.clear()
        listYear.clear()
        for (i in 1..12) {
            listMonth.add(ItemChoice(i, i.toString(), false))
        }
        for (i in 2010..Calendar.getInstance().get(Calendar.YEAR)) {
            listYear.add(ItemChoice(i, i.toString(), false))
        }
        listYear.reverse()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initDataDay(monthChosen: Int, yearChosen: Int) {
        listDay.clear()
        dayPickerAdapter?.indexSelected = -1
        listExpenditure.clear()
        expenditureAdapter?.notifyDataSetChanged()
        val day = when (monthChosen) {
            2 -> if (yearChosen % 4 == 0) 29 else 28
            1, 3, 5, 7, 8, 10 -> 31
            else -> 30
        }
        for (i in 1..day) {
            listDay.add(ItemChoice(i, i.toString(), false))
        }
        dayPickerAdapter?.notifyDataSetChanged()
    }

    private fun openDialogChoice(
        title: String,
        listData: MutableList<ItemChoice>,
        onItemChoice: (idChosen: Int) -> Unit
    ) {
        choiceFragment = ChoiceFragment(
            list_data = listData,
            title,
            itemListener = {
                onItemChoice(it.id)
                choiceFragment?.dismiss()
            },
            onChoiceFragHide = {

            })
        choiceFragment?.show(requireActivity().supportFragmentManager, choiceFragment?.tag ?: "")
    }

    private fun loadData() {
        transactionsViewModel?.run {
            binding?.run {
                refreshLayout.isEnabled = false
                viewBg.setVisibility(true)
                progressBar.setVisibility(true)
                getListExpenditureFromServer(
                    resources.getString(
                        R.string.tokenJWT,
                        Constant.USER.tokenJWT
                    ),
                    Constant.USER.id
                ) { message ->
                    refreshLayout.isEnabled = true
                    refreshLayout.isRefreshing = false
                    viewBg.setVisibility(false)
                    progressBar.setVisibility(false)
                    message?.let {
                        context?.let {
                            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }

}
