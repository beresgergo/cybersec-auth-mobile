package edu.oe.nik.cyber.auth.mobile.ui.registration.prefauth

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import edu.oe.nik.cyber.auth.mobile.network.registration.data.PreferredAuthTypeRequest
import edu.oe.nik.cyber.auth.mobile.network.registration.data.PreferredAuthTypeResponse
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import edu.oe.nik.cyber.auth.mobile.storage.PreferredAuthenticationType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PreferredAuthTypeViewModel @Inject constructor() : ViewModel() {

    lateinit var selection: PreferredAuthenticationType

    val finalizeRegistrationResult: SingleLiveData<FinalizeRegistrationResult> = SingleLiveData()

    @Inject
    lateinit var registrationApi: RegistrationApi

    @Inject
    lateinit var credentialStorage: CredentialStorage

    fun finalizeRegistration() {
        credentialStorage.sessionId?.let {
            val call = registrationApi.finalizeRegistration(credentialStorage.username, PreferredAuthTypeRequest(
                selection.toString(),
                it
            ))

            call.enqueue(object : Callback<PreferredAuthTypeResponse> {
                override fun onResponse(
                    call: Call<PreferredAuthTypeResponse>,
                    response: Response<PreferredAuthTypeResponse>
                ) {
                    credentialStorage.credentialFinalized = true
                    credentialStorage.preferredAuthType = selection
                    finalizeRegistrationResult.postValue(FinalizeRegistrationResult.OK)
                }

                override fun onFailure(call: Call<PreferredAuthTypeResponse>, t: Throwable) {
                    finalizeRegistrationResult.postValue(FinalizeRegistrationResult.NETWORK_FAILURE)
                }
            })
        }
    }
}
enum class FinalizeRegistrationResult {
    NETWORK_FAILURE,
    OK
}