package edu.oe.nik.cyber.auth.mobile.ui.login.totp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dev.turingcomplete.kotlinonetimepassword.TimeBasedOneTimePasswordGenerator
import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.network.login.LoginApi
import edu.oe.nik.cyber.auth.mobile.network.login.data.*
import edu.oe.nik.cyber.auth.mobile.repository.LoginRepository
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TotpLoginViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var loginApi: LoginApi

    @Inject
    lateinit var credentialStorage: CredentialStorage

    @Inject
    lateinit var totpGenerator: TimeBasedOneTimePasswordGenerator

    @Inject
    lateinit var loginRepository: LoginRepository

    var totpToken: MutableLiveData<String> = MutableLiveData()

    fun storeSessionId(sessionId: String) {
        credentialStorage.sessionId = sessionId
    }

    fun refreshTotpCode() {
         totpToken.postValue(totpGenerator.generate())
    }

    fun initiateLogin() {
        loginRepository.initiateLogin(credentialStorage.username)
    }

    fun submitTotpCode() {
        credentialStorage.sessionId?.let { sessionId ->
            loginRepository.submitTotpToken(sessionId, totpGenerator.generate())
        }
    }

    fun storeJwt(jwt: String) {
        credentialStorage.jwt = jwt
    }

    fun getJwtToken() {
        credentialStorage.sessionId?.let { sessionId -> loginRepository.getJWT(sessionId) }
    }
}