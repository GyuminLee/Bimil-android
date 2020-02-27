package com.gyumin.bimil.di

import android.content.Context
import com.gyumin.bimil.BimilApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Main component of the app, created and persisted in the Application class.
 *
 * Whenever a new module is created, it should be added to the list of modules.
 * [AndroidSupportInjectionModule] is the module from Dagger.Android that helps with the
 * generation and location of subcomponents.
 */

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class
    ]
)

interface AppComponent : AndroidInjector<BimilApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}
