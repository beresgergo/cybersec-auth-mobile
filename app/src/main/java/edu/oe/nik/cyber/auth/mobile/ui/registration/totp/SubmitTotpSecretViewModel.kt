package edu.oe.nik.cyber.auth.mobile.ui.registration.totp

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import edu.oe.nik.cyber.auth.mobile.network.registration.data.SubmitTotpTokenRequest
import edu.oe.nik.cyber.auth.mobile.network.registration.data.SubmitTotpTokenResponse
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import org.apache.commons.codec.binary.Base32
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.SecureRandom
import javax.inject.Inject

class SubmitTotpSecretViewModel @Inject constructor() : ViewModel(){

    @Inject
    lateinit var registrationApi: RegistrationApi

    @Inject
    lateinit var credentialStorage: CredentialStorage

    val submitTotpSecretResult: SingleLiveData<SubmitTotpSecretResult> = SingleLiveData()

    fun generateTotpSecret() : String {
        val random = SecureRandom()
        val bytes = ByteArray(32)
        random.nextBytes(bytes)

        val codec = Base32(true)
        return String(codec.encode(bytes))
    }

    fun submitTotpSecret() {
        val totpSecret = generateTotpSecret()
        credentialStorage.sessionId?.let {
            val call = registrationApi.submitTotpSecret(credentialStorage.username, SubmitTotpTokenRequest(it, totpSecret))
            call.enqueue(object: Callback<SubmitTotpTokenResponse>{
                override fun onResponse(
                    call: Call<SubmitTotpTokenResponse>,
                    response: Response<SubmitTotpTokenResponse>
                ) {
                    credentialStorage.totpSecret = totpSecret
                    submitTotpSecretResult.postValue(SubmitTotpSecretResult.OK)
                }

                override fun onFailure(call: Call<SubmitTotpTokenResponse>, t: Throwable) {
                    submitTotpSecretResult.postValue(SubmitTotpSecretResult.NETWORK_FAILURE)
                }

            })
        }
    }
}

enum class SubmitTotpSecretResult {
    NETWORK_FAILURE,
    OK
}
