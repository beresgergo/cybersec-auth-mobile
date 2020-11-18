package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments

import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.ui.registration.prefauth.PreferredAuthTypeViewModel

@Module
object PreferredAuthTypeFragmentModule {

    @Provides
    @JvmStatic
    fun providePreferredAuthTypeViewModel(): PreferredAuthTypeViewModel = PreferredAuthTypeViewModel()
}