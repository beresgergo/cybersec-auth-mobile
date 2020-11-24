package edu.oe.nik.cyber.auth.mobile.ui.registration.prefauth

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import edu.oe.nik.cyber.auth.mobile.repository.RegistrationRepository
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import edu.oe.nik.cyber.auth.mobile.storage.PreferredAuthenticationType
import javax.inject.Inject

class PreferredAuthTypeViewModel @Inject constructor() : ViewModel() {

    lateinit var selection: PreferredAuthenticationType

    @Inject
    lateinit var registrationApi: RegistrationApi

    @Inject
    lateinit var registrationRepository: RegistrationRepository

    @Inject
    lateinit var credentialStorage: CredentialStorage

    fun persistToStorage() {
        credentialStorage.credentialFinalized = true
        credentialStorage.preferredAuthType = selection
    }

    fun finalizeRegistration() {
        credentialStorage.sessionId?.let {
            registrationRepository.finalizeRegistration(credentialStorage.username, it, selection)
        }
    }
}