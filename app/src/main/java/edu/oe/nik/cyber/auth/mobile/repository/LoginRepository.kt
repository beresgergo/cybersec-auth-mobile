package edu.oe.nik.cyber.auth.mobile.repository

import androidx.lifecycle.LiveData
import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.network.login.LoginApi
import edu.oe.nik.cyber.auth.mobile.network.login.data.InitiateLoginResponse
import edu.oe.nik.cyber.auth.mobile.network.login.data.TotpLoginRequest
import edu.oe.nik.cyber.auth.mobile.network.login.data.TotpLoginResponse
import edu.oe.nik.cyber.auth.mobile.network.registration.data.SubmitTotpTokenResponse
import edu.oe.nik.cyber.auth.mobile.ui.login.totp.SubmitTotpTokenResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginApi: LoginApi
) {

    val initiateLoginLiveData = SingleLiveData<InitiateLoginResponse>()

    val submitTotpTokenLiveData = SingleLiveData<TotpLoginResponse>()

    fun initiateLogin(username: String) {
        loginApi.initiateLogin(username).enqueue(object: Callback<InitiateLoginResponse>{
            override fun onResponse(
                call: Call<InitiateLoginResponse>,
                response: Response<InitiateLoginResponse>
            ) {
                initiateLoginLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<InitiateLoginResponse>, t: Throwable) {
                initiateLoginLiveData.postValue(InitiateLoginResponse("", "", "", NETWORK_ERROR))
            }

        })
    }

    fun submitTotpToken(sessionId: String, token: String) {
        loginApi.submitTotpToken(TotpLoginRequest(sessionId, token)).enqueue(object: Callback<TotpLoginResponse> {
            override fun onResponse(
                call: Call<TotpLoginResponse>,
                response: Response<TotpLoginResponse>
            ) {
                submitTotpTokenLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<TotpLoginResponse>, t: Throwable) {
                submitTotpTokenLiveData.postValue(TotpLoginResponse("", NETWORK_ERROR))
            }
        })
    }

    companion object {
        const val NETWORK_ERROR = "NETWORK_ERROR"
    }

}