package com.aizidev.examapps.di.builder

import com.aizidev.examapps.ui.detail.DetailFragment
import com.aizidev.examapps.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuilderModuleMain {

    @ContributesAndroidInjector
    abstract fun contributeHome(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeDetail(): DetailFragment

}