package edu.oe.nik.cyber.auth.mobile.repository

import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.network.NetworkConstants
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import edu.oe.nik.cyber.auth.mobile.network.registration.data.*
import edu.oe.nik.cyber.auth.mobile.storage.PreferredAuthenticationType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RegistrationRepository @Inject constructor(
   private val registrationApi: RegistrationApi
) {
   val startRegistrationResult = SingleLiveData<InitiateRegistrationResponse>()

   val submitTotpSecretResult = SingleLiveData<SubmitTotpTokenResponse>()

   val submitPublicKeyResult = SingleLiveData<SubmitPublicKeyResponse>()

   val finalizeRegistrationResult = SingleLiveData<PreferredAuthTypeResponse>()

   fun startRegistration(username: String) {
      registrationApi.startRegistration(username).enqueue(object: Callback<InitiateRegistrationResponse>{
         override fun onResponse(
            call: Call<InitiateRegistrationResponse>,
            response: Response<InitiateRegistrationResponse>
         ) {
            startRegistrationResult.postValue(when (response.isSuccessful) {
               true -> response.body()
               false -> InitiateRegistrationResponse("", "", "Username is occupied")
            })
         }

         override fun onFailure(call: Call<InitiateRegistrationResponse>, t: Throwable) {
            startRegistrationResult.postValue(InitiateRegistrationResponse("", "", NetworkConstants.NETWORK_ERROR))
         }

      })
   }

   fun submitTotpSecret(username: String, sessionId: String, totpSecret: String) {
      registrationApi
         .submitTotpSecret(username, SubmitTotpTokenRequest(sessionId, totpSecret))
         .enqueue(object: Callback<SubmitTotpTokenResponse> {
            override fun onResponse(
               call: Call<SubmitTotpTokenResponse>,
               response: Response<SubmitTotpTokenResponse>
            ) {
               submitTotpSecretResult.postValue(response.body())
            }

            override fun onFailure(call: Call<SubmitTotpTokenResponse>, t: Throwable) {
               submitTotpSecretResult.postValue(SubmitTotpTokenResponse("", NetworkConstants.NETWORK_ERROR))
            }

         })
   }

   fun submitPublicKey(username: String, sessionId: String, publicKey: String) {
      registrationApi
         .submitPublicKey(username, SubmitPublicKeyRequest(sessionId, publicKey))
         .enqueue(object: Callback<SubmitPublicKeyResponse>{
            override fun onResponse(
               call: Call<SubmitPublicKeyResponse>,
               response: Response<SubmitPublicKeyResponse>
            ) {
               submitPublicKeyResult.postValue(response.body())
            }

            override fun onFailure(call: Call<SubmitPublicKeyResponse>, t: Throwable) {
               submitPublicKeyResult.postValue(SubmitPublicKeyResponse("", NetworkConstants.NETWORK_ERROR))
            }

         })
   }

   fun finalizeRegistration(username: String, sessionId: String, preferredAuthType: PreferredAuthenticationType) {
      registrationApi
         .finalizeRegistration(username, PreferredAuthTypeRequest(sessionId, preferredAuthType.toString()))
         .enqueue(object: Callback<PreferredAuthTypeResponse> {
            override fun onResponse(
               call: Call<PreferredAuthTypeResponse>,
               response: Response<PreferredAuthTypeResponse>
            ) {
               finalizeRegistrationResult.postValue(response.body())
            }

            override fun onFailure(call: Call<PreferredAuthTypeResponse>, t: Throwable) {
               finalizeRegistrationResult.postValue(PreferredAuthTypeResponse("", NetworkConstants.NETWORK_ERROR))
            }

         })
   }

}