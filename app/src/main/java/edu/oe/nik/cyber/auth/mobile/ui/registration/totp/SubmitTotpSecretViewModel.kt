package edu.oe.nik.cyber.auth.mobile.ui.registration.totp

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.repository.RegistrationRepository
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import org.apache.commons.codec.binary.Base32
import java.security.SecureRandom
import javax.inject.Inject

class SubmitTotpSecretViewModel @Inject constructor() : ViewModel(){

    @Inject
    lateinit var registrationRepository: RegistrationRepository

    @Inject
    lateinit var credentialStorage: CredentialStorage

    fun generateTotpSecret() : String {
        val random = SecureRandom()
        val bytes = ByteArray(32)
        random.nextBytes(bytes)

        val codec = Base32(true)
        return String(codec.encode(bytes))
    }

    fun submitTotpSecret() {
        val totpSecret = generateTotpSecret()
        credentialStorage.totpSecret = totpSecret
        credentialStorage.sessionId?.let {
            registrationRepository.submitTotpSecret(credentialStorage.username, it, totpSecret)
        }
    }
}

