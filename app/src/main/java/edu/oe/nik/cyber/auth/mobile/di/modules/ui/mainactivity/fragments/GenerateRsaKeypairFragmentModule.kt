package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments

import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.ui.registration.rsa.GenerateRsaKeypairViewModel

@Module
object GenerateRsaKeypairFragmentModule {

    @Provides
    @JvmStatic
    fun provideGenerateRsaKeypairViewModel(): GenerateRsaKeypairViewModel = GenerateRsaKeypairViewModel()
}