package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments

import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.ui.main.MainFragmentViewModel

@Module
object MainFragmentModule {

    @Provides
    @JvmStatic
    fun provideMainFragmentViewModel(): MainFragmentViewModel = MainFragmentViewModel()
}