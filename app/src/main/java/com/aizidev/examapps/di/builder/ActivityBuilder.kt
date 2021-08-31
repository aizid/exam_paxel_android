package com.aizidev.examapps.di.builder

import com.aizidev.examapps.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuilderModuleMain::class])
    abstract fun contributeMainActivity(): MainActivity

}