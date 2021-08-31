package com.aizidev.examapps.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aizidev.examapps.R
import com.aizidev.examapps.di.component.Injectable
import com.aizidev.examapps.util.NavigationControllerMain
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable {

    @Inject
    lateinit var navigationControllerMain: NavigationControllerMain

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationControllerMain.navigateHome()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.f_main_container)
        if (fragment !is OnBackPressedListner || !(fragment as OnBackPressedListner).onBackPressed()) {
            super.onBackPressed()
        }
    }

    interface OnBackPressedListner {
        fun onBackPressed(): Boolean
    }
}