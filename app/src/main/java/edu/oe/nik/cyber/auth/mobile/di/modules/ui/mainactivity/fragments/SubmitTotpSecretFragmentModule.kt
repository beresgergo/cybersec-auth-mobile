package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments

import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.ui.registration.totp.SubmitTotpSecretViewModel

@Module
object SubmitTotpSecretFragmentModule {

    @Provides
    @JvmStatic
    fun provideSubmitTotpSecretViewModel(): SubmitTotpSecretViewModel = SubmitTotpSecretViewModel()
}