package edu.oe.nik.cyber.auth.mobile.ui.registration.start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.turingcomplete.kotlinonetimepassword.HmacOneTimePasswordGenerator
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import edu.oe.nik.cyber.auth.mobile.network.registration.data.InitiateRegistrationResponse
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import org.apache.commons.codec.binary.Base32
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.security.SecureRandom

import javax.inject.Inject

class StartRegistrationViewModel @Inject constructor() : ViewModel(){

    @Inject
    lateinit var registrationApi: RegistrationApi

    @Inject
    lateinit var credentialStorage: CredentialStorage

    @Inject
    lateinit var totpGenerator: HmacOneTimePasswordGenerator

    val registrationResult: MutableLiveData<InitiateRegistrationResult> = MutableLiveData()

    fun startRegistration() {
        val call = registrationApi.startRegistration(credentialStorage.username)
        call.enqueue(object : Callback<InitiateRegistrationResponse> {
            override fun onResponse(
                call: Call<InitiateRegistrationResponse>,
                response: Response<InitiateRegistrationResponse>
            ) {
                if (response.isSuccessful) {
                    credentialStorage.sessionId = response.body()?.sessionId
                    registrationResult.value = InitiateRegistrationResult.OK
                }
                else {
                    registrationResult.value = InitiateRegistrationResult.USERNAME_ALREADY_REGISTERED
                }
            }
            override fun onFailure(call: Call<InitiateRegistrationResponse>, t: Throwable) {
                // network failure
                registrationResult.value = InitiateRegistrationResult.NETWORK_FAILURE
            }
        })
    }

    fun generateTotpSecret() {
        val random = SecureRandom()
        val bytes = ByteArray(32)
        random.nextBytes(bytes)

        val codec = Base32(true)
        credentialStorage.totpSecret = String(codec.encode(bytes))
        Timber.d( "Encoded secret: %s", credentialStorage.totpSecret)
    }
}

enum class InitiateRegistrationResult {
    NETWORK_FAILURE,
    OK,
    USERNAME_ALREADY_REGISTERED
}
