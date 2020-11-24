package edu.oe.nik.cyber.auth.mobile.ui.login.rsa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.repository.LoginRepository
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import java.security.KeyStore
import java.security.Signature
import org.apache.commons.codec.binary.Base64
import timber.log.Timber
import javax.inject.Inject

class RsaLoginViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var loginRepository: LoginRepository

    @Inject
    lateinit var credentialStorage: CredentialStorage

    @Inject
    lateinit var privateKeyEntry: KeyStore.PrivateKeyEntry

    @Inject
    lateinit var signature: Signature

    var challenge: MutableLiveData<String> = MutableLiveData()

    fun storeSessionId(sessionId: String) {
        credentialStorage.sessionId = sessionId
    }

    fun initiateLogin() {
        loginRepository.initiateLogin(credentialStorage.username)
    }

    fun retrieveChallenge() {
        credentialStorage.sessionId?.let {
            loginRepository.getLoginChallenge(it)
        }
    }

    fun updateChallenge(challengeValue: String) {
        challenge.postValue(challengeValue)
    }

    fun initSign() {
        signature.initSign(privateKeyEntry.privateKey)
    }

    fun signChallenge(signature: Signature) {
        credentialStorage.sessionId?.let {
            val signedChallenge = Base64.encodeBase64String(signature.run {
                update(challenge.value?.toByteArray())
                sign()
            })
            loginRepository.submitSignedChallenge(it, signedChallenge)
        }
    }
}