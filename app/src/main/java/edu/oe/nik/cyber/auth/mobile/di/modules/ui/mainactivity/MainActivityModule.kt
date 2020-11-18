package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments.GenerateRsaKeypairFragmentModule
import edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments.StartRegistrationFragmentModule
import edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments.SubmitTotpSecretFragmentModule
import edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments.TotpLoginFragmentModule
import edu.oe.nik.cyber.auth.mobile.ui.login.totp.TotpLoginFragment
import edu.oe.nik.cyber.auth.mobile.ui.registration.rsa.GenerateRsaKeypairFragment
import edu.oe.nik.cyber.auth.mobile.ui.registration.start.StartRegistrationFragment
import edu.oe.nik.cyber.auth.mobile.ui.registration.totp.SubmitTotpSecretFragment

@Module
interface MainActivityModule {

    @ContributesAndroidInjector(modules = [StartRegistrationFragmentModule::class])
    fun bindStartRegistrationFragmentModule(): StartRegistrationFragment

    @ContributesAndroidInjector(modules = [TotpLoginFragmentModule::class])
    fun bindTotpLoginFragmentModule(): TotpLoginFragment

    @ContributesAndroidInjector(modules = [SubmitTotpSecretFragmentModule::class])
    fun bindSubmitTotpSecretFragmentModule(): SubmitTotpSecretFragment

    @ContributesAndroidInjector(modules = [GenerateRsaKeypairFragmentModule::class])
    fun bindGenerateRsaKeypairFragmentModule(): GenerateRsaKeypairFragment
}