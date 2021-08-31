package com.aizidev.examapps.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aizidev.examapps.AppExecutors
import com.aizidev.examapps.R
import com.aizidev.examapps.activity.MainActivity
import com.aizidev.examapps.binding.FragmentDataBindingComponent
import com.aizidev.examapps.databinding.HomeFragmentBinding
import com.aizidev.examapps.di.component.Injectable
import com.aizidev.examapps.ui.common.RetryCallback
import com.aizidev.examapps.ui.home.adapter.AdapterHomeEmp
import com.aizidev.examapps.util.NavigationControllerMain
import com.aizidev.examapps.util.autoCleared
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable, MainActivity.OnBackPressedListner {
    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var navigationControllerMain: NavigationControllerMain

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding by autoCleared<HomeFragmentBinding>()
    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }
    private var adapter by autoCleared<AdapterHomeEmp>()

    fun newInstance(): HomeFragment {
        return HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = DataBindingUtil.inflate<HomeFragmentBinding>(
            inflater,
            R.layout.home_fragment,
            container,
            false,
            dataBindingComponent
        )
        dataBinding.retryCallback = object : RetryCallback {
            override fun retry() {
                viewModel.retry()
            }
        }
        binding = dataBinding

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setupRetry("GET_")
        binding.lifecycleOwner = viewLifecycleOwner

        setupAdapter()
        setupComponent()
        initObserveble()
        setupApi()
    }

    private fun initObserveble() {
        viewModel.employee.observe(viewLifecycleOwner, Observer { listResource ->
            if (listResource?.data != null && listResource.data.isNotEmpty()) {
                adapter.submitList(listResource.data)
                adapter.mFilteredListConstant = listResource.data
            } else {
                adapter.submitList(emptyList())
            }
        })
    }

    private fun setupApi() {

    }

    private fun setupComponent() {
        binding.swplHomeFragmentRefresh.setOnRefreshListener {
            viewModel.retry()
            Handler().postDelayed({
                binding.swplHomeFragmentRefresh.isRefreshing = false
            }, 1500)
        }

        binding.srvSearchList.imeOptions = EditorInfo.IME_ACTION_DONE
        binding.srvSearchList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.length > 2) {
                    adapter.filter.filter(query)
                } else {
                    adapter.filter.filter("")
                }
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length > 2) {
                    adapter.filter.filter(newText)
                } else {
                    adapter.filter.filter("")
                }
                return false;
            }
        });
    }

    private fun setupAdapter() {
        val adapter = AdapterHomeEmp(dataBindingComponent, appExecutors) {
//                employeeRes ->
        }
        this.adapter = adapter
        binding.rcvHomeFragmentEmp.adapter = adapter
    }

    override fun onBackPressed(): Boolean {
        activity?.finish()
        return true
    }

}