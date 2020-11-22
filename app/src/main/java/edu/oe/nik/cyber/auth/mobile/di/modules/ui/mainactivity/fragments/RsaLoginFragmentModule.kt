package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments

import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.ui.login.rsa.RsaLoginViewModel

@Module
object RsaLoginFragmentModule {

    @Provides
    @JvmStatic
    fun provideRsaLoginViewModel(): RsaLoginViewModel = RsaLoginViewModel()

}