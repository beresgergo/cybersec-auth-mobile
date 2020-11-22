package edu.oe.nik.cyber.auth.mobile.di.modules.repository

import dagger.Module
import dagger.Provides
import edu.oe.nik.cyber.auth.mobile.network.login.LoginApi
import edu.oe.nik.cyber.auth.mobile.network.management.ManagementApi
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import edu.oe.nik.cyber.auth.mobile.repository.LoginRepository
import edu.oe.nik.cyber.auth.mobile.repository.ManagementRepository
import edu.oe.nik.cyber.auth.mobile.repository.RegistrationRepository
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRegistrationRepository(registrationApi: RegistrationApi) = RegistrationRepository(registrationApi)

    @Provides
    @Singleton
    fun provideLoginRepository(loginApi: LoginApi) = LoginRepository(loginApi)

    @Provides
    @Singleton
    fun provideManagementRepository(managementApi: ManagementApi) = ManagementRepository(managementApi)
}