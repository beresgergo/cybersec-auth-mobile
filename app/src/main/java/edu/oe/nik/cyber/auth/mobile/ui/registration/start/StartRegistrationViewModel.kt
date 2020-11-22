package edu.oe.nik.cyber.auth.mobile.ui.registration.start

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import edu.oe.nik.cyber.auth.mobile.network.registration.data.InitiateRegistrationResponse
import edu.oe.nik.cyber.auth.mobile.repository.LoginRepository
import edu.oe.nik.cyber.auth.mobile.repository.ManagementRepository
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.log

class StartRegistrationViewModel @Inject constructor() : ViewModel(){

    @Inject
    lateinit var registrationApi: RegistrationApi

    @Inject
    lateinit var credentialStorage: CredentialStorage

    val registrationResult: SingleLiveData<InitiateRegistrationResult> = SingleLiveData()

    fun startRegistration() {
        val call = registrationApi.startRegistration(credentialStorage.username)
        call.enqueue(object : Callback<InitiateRegistrationResponse> {
            override fun onResponse(
                call: Call<InitiateRegistrationResponse>,
                response: Response<InitiateRegistrationResponse>
            ) {
                if (response.isSuccessful) {
                    credentialStorage.sessionId = response.body()?.sessionId
                    registrationResult.postValue(InitiateRegistrationResult.OK)
                }
                else {
                    registrationResult.postValue(InitiateRegistrationResult.USERNAME_ALREADY_REGISTERED)
                }
            }
            override fun onFailure(call: Call<InitiateRegistrationResponse>, t: Throwable) {
                // network failure
                registrationResult.postValue(InitiateRegistrationResult.NETWORK_FAILURE)
            }
        })
    }

}

enum class InitiateRegistrationResult {
    NETWORK_FAILURE,
    OK,
    USERNAME_ALREADY_REGISTERED
}
