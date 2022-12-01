package com.test.cdcn_appmobile.ui.main.transactions.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.cdcn_appmobile.data.models.Expenditure
import com.test.cdcn_appmobile.databinding.ItemExpenditureBinding
import com.test.cdcn_appmobile.extension.toStringNumber

class ExpenditureAdapter(
    private val listData: MutableList<Expenditure>,
    private val onItemClick:(Expenditure) -> Unit
) : RecyclerView.Adapter<ExpenditureAdapter.ExpenditureHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenditureHolder {
        val binding =
            ItemExpenditureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenditureHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenditureHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = listData.size

    inner class ExpenditureHolder(private val binding: ItemExpenditureBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        internal fun bindView(position: Int) {
            binding.run {
                tvName.text = listData[position].categoryTypeName
                tvMoney.text = "${listData[position].cost.toStringNumber()} đ"
                tvMoney.isSelected = listData[position].categoryType == 0

                layoutItem.setOnClickListener {
                    onItemClick(listData[position])
                }
            }
        }
    }
}