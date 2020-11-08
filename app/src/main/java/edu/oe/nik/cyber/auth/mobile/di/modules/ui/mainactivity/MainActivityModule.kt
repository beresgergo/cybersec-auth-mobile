package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments.MainFragmentModule
import edu.oe.nik.cyber.auth.mobile.ui.registration.start.StartRegistrationFragment

@Module
interface MainActivityModule {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    fun bindMainFragment(): StartRegistrationFragment
}