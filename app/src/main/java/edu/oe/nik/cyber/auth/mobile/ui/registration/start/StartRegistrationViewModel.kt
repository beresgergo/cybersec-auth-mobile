package edu.oe.nik.cyber.auth.mobile.ui.registration.start

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.repository.RegistrationRepository
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import javax.inject.Inject

class StartRegistrationViewModel @Inject constructor() : ViewModel(){

    @Inject
    lateinit var registrationRepository: RegistrationRepository

    @Inject
    lateinit var credentialStorage: CredentialStorage

    fun storeSessionId(sessionId: String) {
        credentialStorage.sessionId = sessionId
    }

    fun startRegistration() {
        registrationRepository.startRegistration(credentialStorage.username)
    }

}
