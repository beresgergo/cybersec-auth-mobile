package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments

import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.ui.authenticated.AuthenticatedViewModel

@Module
object AuthenticatedFragmentModule {

    @Provides
    @JvmStatic
    fun provideAuthenticatedViewModel(): AuthenticatedViewModel = AuthenticatedViewModel()
}