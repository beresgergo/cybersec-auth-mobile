package edu.oe.nik.cyber.auth.mobile.repository

import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.network.NetworkConstants
import edu.oe.nik.cyber.auth.mobile.network.login.LoginApi
import edu.oe.nik.cyber.auth.mobile.network.login.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginApi: LoginApi
) {

    val initiateLoginResult = SingleLiveData<InitiateLoginResponse>()

    val submitTotpTokenResult = SingleLiveData<TotpLoginResponse>()

    val getLoginChallengeResult = SingleLiveData<LoginChallengeResponse>()

    val submitSignedChallengeResult = SingleLiveData<SignedChallengeResponse>()

    val getJwtResult = SingleLiveData<RetrieveTokenResponse>()

    fun initiateLogin(username: String) {
        loginApi.initiateLogin(username).enqueue(object: Callback<InitiateLoginResponse>{
            override fun onResponse(
                call: Call<InitiateLoginResponse>,
                response: Response<InitiateLoginResponse>
            ) {
                initiateLoginResult.postValue(response.body())
            }

            override fun onFailure(call: Call<InitiateLoginResponse>, t: Throwable) {
                initiateLoginResult.postValue(InitiateLoginResponse("", "", "", NetworkConstants.NETWORK_ERROR))
            }

        })
    }

    fun submitTotpToken(sessionId: String, token: String) {
        loginApi.submitTotpToken(TotpLoginRequest(sessionId, token)).enqueue(object: Callback<TotpLoginResponse> {
            override fun onResponse(
                call: Call<TotpLoginResponse>,
                response: Response<TotpLoginResponse>
            ) {
                submitTotpTokenResult.postValue(when(response.isSuccessful) {
                    true -> response.body()
                    false -> TotpLoginResponse("", "Invalid token.")
                })
            }
            override fun onFailure(call: Call<TotpLoginResponse>, t: Throwable) {
                submitTotpTokenResult.postValue(TotpLoginResponse("", NetworkConstants.NETWORK_ERROR))
            }
        })
    }

    fun getLoginChallenge(sessionId: String) {
        loginApi.getLoginChallenge(LoginChallengeRequest(sessionId)).enqueue(object: Callback<LoginChallengeResponse> {
            override fun onResponse(
                call: Call<LoginChallengeResponse>,
                response: Response<LoginChallengeResponse>
            ) {
                getLoginChallengeResult.postValue(response.body())
            }

            override fun onFailure(call: Call<LoginChallengeResponse>, t: Throwable) {
                getLoginChallengeResult.postValue(LoginChallengeResponse("", "", NetworkConstants.NETWORK_ERROR))
            }

        })
    }

    fun submitSignedChallenge(sessionId: String, signedChallenge: String) {
        loginApi.submitSignedChallenge(SignedChallengeRequest(sessionId, signedChallenge)).enqueue(object: Callback<SignedChallengeResponse> {
            override fun onResponse(
                call: Call<SignedChallengeResponse>,
                response: Response<SignedChallengeResponse>
            ) {
                submitSignedChallengeResult.postValue(response.body())
            }

            override fun onFailure(call: Call<SignedChallengeResponse>, t: Throwable) {
                submitSignedChallengeResult.postValue(SignedChallengeResponse("", NetworkConstants.NETWORK_ERROR))
            }

        })
    }

    fun getJWT(sessionId: String) {
        loginApi.retrieveToken(RetrieveTokenRequest(sessionId)).enqueue(object: Callback<RetrieveTokenResponse> {
            override fun onResponse(
                call: Call<RetrieveTokenResponse>,
                response: Response<RetrieveTokenResponse>
            ) {
                getJwtResult.postValue(response.body())
            }

            override fun onFailure(call: Call<RetrieveTokenResponse>, t: Throwable) {
                getJwtResult.postValue(RetrieveTokenResponse("", "", NetworkConstants.NETWORK_ERROR))
            }

        })
    }

}