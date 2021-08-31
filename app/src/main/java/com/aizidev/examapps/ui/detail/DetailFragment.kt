package com.aizidev.examapps.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.aizidev.examapps.AppExecutors
import com.aizidev.examapps.R
import com.aizidev.examapps.activity.MainActivity
import com.aizidev.examapps.binding.FragmentDataBindingComponent
import com.aizidev.examapps.databinding.DetailFragmentBinding
import com.aizidev.examapps.databinding.HomeFragmentBinding
import com.aizidev.examapps.di.component.Injectable
import com.aizidev.examapps.ui.home.HomeFragment
import com.aizidev.examapps.ui.home.HomeViewModel
import com.aizidev.examapps.util.NavigationControllerMain
import com.aizidev.examapps.util.autoCleared
import javax.inject.Inject

class DetailFragment : Fragment(), Injectable, MainActivity.OnBackPressedListner {
    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var navigationControllerMain: NavigationControllerMain

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding by autoCleared<DetailFragmentBinding>()
    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private val detailViewModel: DetailViewModel by viewModels {
        viewModelFactory
    }

    fun newInstance(id: Int): DetailFragment {
        return DetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = DataBindingUtil.inflate<DetailFragmentBinding>(
            inflater,
            R.layout.detail_fragment,
            container,
            false,
            dataBindingComponent
        )
        binding = dataBinding

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onBackPressed(): Boolean {
        return false
    }

}