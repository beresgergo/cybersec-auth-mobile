package edu.oe.nik.cyber.auth.mobile.ui.registration.start

import android.util.Log
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

    val hello: String = "Hello from the view model, again"

    fun updateHello() {
        Log.d("TAG", credentialStorage.jwt)
        credentialStorage.jwt = "let's test it"
        Log.d("TAG", credentialStorage.jwt)
    }


    fun startRegistration() {
        val call = registrationApi.startRegistration("yolo")
    }

    fun generateTotpSecret() {
        val random = SecureRandom()
        val bytes = ByteArray(32)
        random.nextBytes(bytes)

        val codec = Base32(false);
        credentialStorage.totpSecret = String(codec.encode(bytes))
        Log.d("TAG", "Encoded secret: " + credentialStorage.totpSecret)
    }
}