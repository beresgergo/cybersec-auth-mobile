package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments

import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.ui.login.totp.TotpLoginViewModel

@Module
object TotpLoginFragmentModule {

    @Provides
    @JvmStatic
    fun provideTotpLoginFragmentViewModel(): TotpLoginViewModel = TotpLoginViewModel()
}