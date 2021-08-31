package com.aizidev.examapps.util

import androidx.fragment.app.FragmentManager
import com.aizidev.examapps.R
import com.aizidev.examapps.activity.MainActivity
import com.aizidev.examapps.ui.detail.DetailFragment
import com.aizidev.examapps.ui.home.HomeFragment
import javax.inject.Inject

open class NavigationControllerMain @Inject constructor(mainActivity: MainActivity) {

    private var containerId: Int = R.id.f_main_container
    private var fragmentManager: FragmentManager = mainActivity.supportFragmentManager

    fun navigateHome() {
        val homeFragment = HomeFragment().newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId, homeFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun navigateDetail(id: Int) {
        val detailFragment = DetailFragment().newInstance(id)
        fragmentManager.beginTransaction()
            .replace(containerId, detailFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

}