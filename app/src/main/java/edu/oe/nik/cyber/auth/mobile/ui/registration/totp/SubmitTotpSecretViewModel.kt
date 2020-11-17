package edu.oe.nik.cyber.auth.mobile.ui.registration.totp

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import org.apache.commons.codec.binary.Base32
import timber.log.Timber
import java.security.SecureRandom
import javax.inject.Inject

class SubmitTotpSecretViewModel @Inject constructor() : ViewModel(){

    @Inject
    lateinit var registrationApi: RegistrationApi

    @Inject
    lateinit var credentialStorage: CredentialStorage

    fun generateTotpSecret() {
        val random = SecureRandom()
        val bytes = ByteArray(32)
        random.nextBytes(bytes)

        val codec = Base32(true)
        credentialStorage.totpSecret = String(codec.encode(bytes))
        Timber.d( "Encoded secret: %s", credentialStorage.totpSecret)
    }

    fun submitTotpSecret() {

    }
}