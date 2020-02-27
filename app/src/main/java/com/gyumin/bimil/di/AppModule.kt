package com.gyumin.bimil.di

import android.content.Context
import com.gyumin.bimil.BimilApplication
import dagger.Module
import dagger.Provides


/**
 * Defines all the classes that need to be provided in the scope of the app.
 *
 * Define here all objects that are shared throughout the app, like SharedPreferences, navigators or
 * others. If some of those objects are singletons, they should be annotated with `@Singleton`.
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: BimilApplication): Context {
        return application.applicationContext
    }
}
