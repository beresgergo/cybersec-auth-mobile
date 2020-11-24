package edu.oe.nik.cyber.auth.mobile.ui.registration.rsa

import android.security.keystore.KeyGenParameterSpec
import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.repository.RegistrationRepository
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import org.apache.commons.codec.binary.Base64
import timber.log.Timber
import java.security.KeyPairGenerator
import java.security.PublicKey
import javax.inject.Inject

class GenerateRsaKeypairViewModel @Inject constructor() : ViewModel() {

    val publicKeyGenerated: SingleLiveData<Boolean> = SingleLiveData()

    @Inject
    lateinit var keypairGenerator: KeyPairGenerator

    @Inject
    lateinit var keyGenParameterSpec: KeyGenParameterSpec

    @Inject
    lateinit var registrationRepository: RegistrationRepository

    @Inject
    lateinit var credentialStorage: CredentialStorage

    private lateinit var publicKey: PublicKey

    fun PublicKey.base64String(): String {
        val pemText = Base64.encodeBase64String(encoded)
        val formattedPem = pemText.chunked(PEM_LINE_LENGTH) { "$it" }.joinToString(separator = NEW_LINE)

        return Base64.encodeBase64String("$PEM_PREFIX_PUBLIC_KEY$NEW_LINE$formattedPem$NEW_LINE$PEM_POSTFIX_PUBLIC_KEY".toByteArray())
    }

    fun generatePublicKey() {
        keypairGenerator.initialize(keyGenParameterSpec)
        publicKey = keypairGenerator.generateKeyPair().public
        publicKeyGenerated.postValue(true)
    }

    fun submitPublicKey() {

        credentialStorage.sessionId?.let {
            Timber.d("FORMAT %s", publicKey.base64String())
            registrationRepository.submitPublicKey(credentialStorage.username, it, publicKey.base64String())
        }
    }

    companion object {
        private const val PEM_LINE_LENGTH = 64
        private const val PEM_PREFIX_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----"
        private const val PEM_POSTFIX_PUBLIC_KEY = "-----END PUBLIC KEY-----"
        private const val NEW_LINE = "\n"
    }
}
