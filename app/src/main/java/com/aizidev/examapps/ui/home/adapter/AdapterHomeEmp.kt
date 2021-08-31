package com.aizidev.examapps.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.aizidev.examapps.AppExecutors
import com.aizidev.examapps.R
import com.aizidev.examapps.databinding.LayoutItemRcvEmployeeHomeBinding
import com.aizidev.examapps.repository.GlobalRepo
import com.aizidev.examapps.ui.common.DataBoundListAdapter
import com.aizidev.examapps.util.GlobalFunc
import com.aizidev.examapps.vo.DataItem

class AdapterHomeEmp(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val clickCallback: ((DataItem) -> Unit)?
) : DataBoundListAdapter<DataItem, LayoutItemRcvEmployeeHomeBinding>(
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
), Filterable {
    var mFilteredList: MutableList<DataItem> = currentList
    var mFilteredListConstant: List<DataItem> = listOf()

    override fun createBinding(parent: ViewGroup): LayoutItemRcvEmployeeHomeBinding {
        val binding = DataBindingUtil
            .inflate<LayoutItemRcvEmployeeHomeBinding>(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_rcv_employee_home,
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

    override fun bind(binding: LayoutItemRcvEmployeeHomeBinding, item: DataItem) {
        binding.posthome = item
        binding.tvItemRcvEmpHomeSalary.text = "IDR " + GlobalFunc.GET_FORMAT_THOUSAND_SEPARATOR_ONE(item.employeeSalary)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {

                val charString = charSequence.toString().toLowerCase()

                if (charString.isEmpty()) {
                    mFilteredList = mFilteredListConstant.toMutableList()
                } else {

                    val filteredList = currentList
                        .filter { it.employeeName?.toLowerCase()?.contains(charString)!! }
                        .toMutableList()

                    mFilteredList = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = mFilteredList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                submitList(filterResults.values as MutableList<DataItem>)
                notifyDataSetChanged()
            }
        }
    }
}