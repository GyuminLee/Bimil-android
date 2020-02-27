package com.gyumin.bimil.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Dagger module for the tasks list feature.
 */
@Module
abstract class SecretModule {

//    @ContributesAndroidInjector(modules = [
//        ViewModelBuilder::class
//    ])

//    internal abstract fun tasksFragment(): TasksFragment
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(TasksViewModel::class)
//    abstract fun bindViewModel(viewmodel: TasksViewModel): ViewModel
}
