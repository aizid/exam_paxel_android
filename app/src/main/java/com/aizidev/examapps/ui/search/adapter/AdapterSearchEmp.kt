package com.aizidev.examapps.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.aizidev.examapps.AppExecutors
import com.aizidev.examapps.R
import com.aizidev.examapps.databinding.LayoutItemRcvEmployeeSearchBinding
import com.aizidev.examapps.ui.common.DataBoundListAdapter
import com.aizidev.examapps.vo.DataItem

class AdapterSearchEmp(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val clickCallback: ((DataItem) -> Unit)?
) : DataBoundListAdapter<DataItem, LayoutItemRcvEmployeeSearchBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.profileImage == newItem.profileImage
                    && oldItem.employeeName == newItem.employeeName
                    && oldItem.employeeSalary == newItem.employeeSalary
                    && oldItem.employeeAge == newItem.employeeAge
        }
    }
) {

    override fun createBinding(parent: ViewGroup): LayoutItemRcvEmployeeSearchBinding {
        val binding = DataBindingUtil
            .inflate<LayoutItemRcvEmployeeSearchBinding>(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_rcv_employee_search,
                parent,
                false,
                dataBindingComponent
            )

        binding.root.setOnClickListener {
            binding.posthome?.let {
                clickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: LayoutItemRcvEmployeeSearchBinding, item: DataItem) {
        binding.posthome = item
    }
}