package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments

import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.ui.registration.start.StartRegistrationViewModel

@Module
object MainFragmentModule {

    @Provides
    @JvmStatic
    fun provideMainFragmentViewModel(): StartRegistrationViewModel = StartRegistrationViewModel()
}