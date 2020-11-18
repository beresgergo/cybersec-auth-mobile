package edu.oe.nik.cyber.auth.mobile.di.modules.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import edu.oe.nik.cyber.auth.mobile.ViewModelFactory
import edu.oe.nik.cyber.auth.mobile.di.qualifiers.ViewModelKey
import edu.oe.nik.cyber.auth.mobile.ui.login.totp.TotpLoginViewModel
import edu.oe.nik.cyber.auth.mobile.ui.registration.prefauth.PreferredAuthTypeViewModel
import edu.oe.nik.cyber.auth.mobile.ui.registration.rsa.GenerateRsaKeypairViewModel
import edu.oe.nik.cyber.auth.mobile.ui.registration.start.StartRegistrationViewModel
import edu.oe.nik.cyber.auth.mobile.ui.registration.totp.SubmitTotpSecretViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
	@IntoMap
	@ViewModelKey(StartRegistrationViewModel::class)
	fun bindStartRegistrationFragmentViewModel(startRegistrationViewModel: StartRegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TotpLoginViewModel::class)
    fun bindTotpLoginFragmentViewModel(totpLoginViewModel: TotpLoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GenerateRsaKeypairViewModel::class)
    fun bindGenerateRsaKeypairViewModel(generateRsaKeypairViewModel: GenerateRsaKeypairViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SubmitTotpSecretViewModel::class)
    fun bindSubmitTotpSecretViewModel(submitTotpSecretViewModel: SubmitTotpSecretViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PreferredAuthTypeViewModel::class)
    fun bindPreferredAuthTypeViewModel(preferredAuthTypeViewModel: PreferredAuthTypeViewModel): ViewModel
}