package edu.oe.nik.cyber.auth.mobile.di.modules.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import edu.oe.nik.cyber.auth.mobile.ViewModelFactory
import edu.oe.nik.cyber.auth.mobile.di.qualifiers.ViewModelKey
import edu.oe.nik.cyber.auth.mobile.ui.login.totp.TotpLoginViewModel
import edu.oe.nik.cyber.auth.mobile.ui.registration.start.StartRegistrationViewModel

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
}