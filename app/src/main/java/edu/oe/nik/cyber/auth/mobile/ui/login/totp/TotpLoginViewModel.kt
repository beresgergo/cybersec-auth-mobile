package edu.oe.nik.cyber.auth.mobile.ui.login.totp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.turingcomplete.kotlinonetimepassword.TimeBasedOneTimePasswordGenerator
import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.network.login.LoginApi
import edu.oe.nik.cyber.auth.mobile.network.login.data.InitiateLoginResponse
import edu.oe.nik.cyber.auth.mobile.network.login.data.TotpLoginRequest
import edu.oe.nik.cyber.auth.mobile.network.login.data.TotpLoginResponse
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
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

    var initiateLoginResult: SingleLiveData<InitiateLoginResult> = SingleLiveData()

    var submitTotpTokenResult: SingleLiveData<SubmitTotpTokenResult> = SingleLiveData()

    var totpToken: MutableLiveData<String> = MutableLiveData()

    fun refreshTotpCode() {
         totpToken.postValue(totpGenerator.generate())
    }

    fun initiateLogin() {
        val call = loginApi.initiateLogin(credentialStorage.username)
        call.enqueue(object: Callback<InitiateLoginResponse>{
            override fun onResponse(
                call: Call<InitiateLoginResponse>,
                response: Response<InitiateLoginResponse>
            ) {
                credentialStorage.sessionId = response.body()?.sessionId
                initiateLoginResult.postValue(InitiateLoginResult.OK)
            }

            override fun onFailure(call: Call<InitiateLoginResponse>, t: Throwable) {
                initiateLoginResult.postValue(InitiateLoginResult.NETWORK_FAILURE)
            }

        })
    }

    fun submitTotpCode() {
        credentialStorage.sessionId?.let { sessionId ->
            val call = loginApi.submitTotpToken(TotpLoginRequest(
                sessionId,
                totpGenerator.generate()
            ))

            call.enqueue(object: Callback<TotpLoginResponse> {
                override fun onResponse(
                    call: Call<TotpLoginResponse>,
                    response: Response<TotpLoginResponse>
                ) {
                    response.body()?.let {
                        when (it.status) {
                            "OK" -> submitTotpTokenResult.postValue(SubmitTotpTokenResult.OK)
                            else -> submitTotpTokenResult.postValue(SubmitTotpTokenResult.INVALID_TOKEN)
                        }
                    }
                }

                override fun onFailure(call: Call<TotpLoginResponse>, t: Throwable) {
                    submitTotpTokenResult.postValue(SubmitTotpTokenResult.NEWORK_FAILURE)
                }
            })
        }

    }
}

enum class InitiateLoginResult {
    OK,
    NETWORK_FAILURE
}

enum class SubmitTotpTokenResult {
    OK,
    NEWORK_FAILURE,
    INVALID_TOKEN
}