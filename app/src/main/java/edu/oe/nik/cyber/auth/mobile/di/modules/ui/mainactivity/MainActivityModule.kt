package edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import edu.oe.nik.cyber.auth.mobile.di.modules.ui.mainactivity.fragments.*
import edu.oe.nik.cyber.auth.mobile.ui.authenticated.AuthenticatedFragment
import edu.oe.nik.cyber.auth.mobile.ui.login.rsa.RsaLoginFragment
import edu.oe.nik.cyber.auth.mobile.ui.login.totp.TotpLoginFragment
import edu.oe.nik.cyber.auth.mobile.ui.registration.prefauth.PreferredAuthTypeFragment
import edu.oe.nik.cyber.auth.mobile.ui.registration.rsa.GenerateRsaKeypairFragment
import edu.oe.nik.cyber.auth.mobile.ui.registration.start.StartRegistrationFragment
import edu.oe.nik.cyber.auth.mobile.ui.registration.totp.SubmitTotpSecretFragment

@Module
interface MainActivityModule {

    @ContributesAndroidInjector(modules = [StartRegistrationFragmentModule::class])
    fun bindStartRegistrationFragment(): StartRegistrationFragment

    @ContributesAndroidInjector(modules = [TotpLoginFragmentModule::class])
    fun bindTotpLoginFragment(): TotpLoginFragment

    @ContributesAndroidInjector(modules = [RsaLoginFragmentModule::class])
    fun binsRsaLoginFragment(): RsaLoginFragment

    @ContributesAndroidInjector(modules = [SubmitTotpSecretFragmentModule::class])
    fun bindSubmitTotpSecretFragment(): SubmitTotpSecretFragment

    @ContributesAndroidInjector(modules = [GenerateRsaKeypairFragmentModule::class])
    fun bindGenerateRsaKeypairFragment(): GenerateRsaKeypairFragment

    @ContributesAndroidInjector(modules = [PreferredAuthTypeFragmentModule::class])
    fun bindPreferredAuthTypeFragment(): PreferredAuthTypeFragment

    @ContributesAndroidInjector(modules = [AuthenticatedFragmentModule::class])
    fun bindAuthenticatedFragment(): AuthenticatedFragment
}