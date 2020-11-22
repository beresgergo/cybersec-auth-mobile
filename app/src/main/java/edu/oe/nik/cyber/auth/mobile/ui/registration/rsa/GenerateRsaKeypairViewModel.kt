package edu.oe.nik.cyber.auth.mobile.ui.registration.rsa

import android.security.keystore.KeyGenParameterSpec
import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import edu.oe.nik.cyber.auth.mobile.network.registration.data.SubmitPublicKeyRequest
import edu.oe.nik.cyber.auth.mobile.network.registration.data.SubmitPublicKeyResponse
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import org.apache.commons.codec.binary.Base64
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.security.KeyPairGenerator
import java.security.PublicKey
import javax.inject.Inject

class GenerateRsaKeypairViewModel @Inject constructor() : ViewModel() {

    val publicKeyGenerated: SingleLiveData<Boolean> = SingleLiveData()

    val submitRsaPublicKeyResult: SingleLiveData<SubmitPublicKeyResult> = SingleLiveData()

    @Inject
    lateinit var keypairGenerator: KeyPairGenerator

    @Inject
    lateinit var keyGenParameterSpec: KeyGenParameterSpec

    @Inject
    lateinit var registrationApi: RegistrationApi

    @Inject
    lateinit var credentialStorage: CredentialStorage

    private lateinit var publicKey: PublicKey

    fun generatePublicKey() {
        keypairGenerator.initialize(keyGenParameterSpec)
        publicKey = keypairGenerator.generateKeyPair().public
        publicKeyGenerated.postValue(true)
    }

    fun submitPublicKey() {

        credentialStorage.sessionId?.let {
            Timber.d("FORMAT " + publicKey.format)
            val call = registrationApi.submitPublicKey(credentialStorage.username, SubmitPublicKeyRequest(
                it,
                Base64.encodeBase64String(publicKey.encoded)
            ))

            call.enqueue(object : Callback<SubmitPublicKeyResponse> {
                override fun onResponse(
                    call: Call<SubmitPublicKeyResponse>,
                    response: Response<SubmitPublicKeyResponse>
                ) {
                    submitRsaPublicKeyResult.postValue(SubmitPublicKeyResult.OK)
                }

                override fun onFailure(call: Call<SubmitPublicKeyResponse>, t: Throwable) {
                    submitRsaPublicKeyResult.postValue(SubmitPublicKeyResult.NETWORK_FAILURE)
                }

            })
        }
    }
}

enum class SubmitPublicKeyResult {
    NETWORK_FAILURE,
    OK
}