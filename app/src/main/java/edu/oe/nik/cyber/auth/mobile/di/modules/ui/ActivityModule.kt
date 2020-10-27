package edu.oe.nik.cyber.auth.mobile.di.modules.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector
import edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.MainActivityModule
import edu.oe.nik.cyber.auth.mobile.di.scopes.ActivityScoped
import edu.oe.nik.cyber.auth.mobile.ui.main.MainActivity

@Module
interface ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun providesMainActivity(): MainActivity
}