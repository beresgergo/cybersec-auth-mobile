package edu.oe.nik.cyber.auth.mobile.di.modules.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import edu.oe.nik.cyber.auth.mobile.ViewModelFactory
import edu.oe.nik.cyber.auth.mobile.di.qualifiers.ViewModelKey
import edu.oe.nik.cyber.auth.mobile.ui.main.MainFragmentViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
	@IntoMap
	@ViewModelKey(MainFragmentViewModel::class)
	fun bindMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel
}