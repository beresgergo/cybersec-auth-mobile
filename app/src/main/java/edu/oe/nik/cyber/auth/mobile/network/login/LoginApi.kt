package edu.oe.nik.cyber.auth.mobile.network.login

import edu.oe.nik.cyber.auth.mobile.network.NetworkConstants
import edu.oe.nik.cyber.auth.mobile.network.login.data.*
import retrofit2.Call
import retrofit2.http.*

interface LoginApi {

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @GET("/login/{username}")
    fun initiateLogin(
        @Path("username") username: String
    ): Call<InitiateLoginResponse>

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @POST("/login/otpToken")
    fun submitTotpToken(
        @Body body: TotpLoginRequest
    ): Call<TotpLoginResponse>

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @POST("/login/challenge")
    fun getLoginChallenge(
        @Body body: LoginChallengeRequest
    ): Call<LoginChallengeResponse>

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @POST("/login/signedChallenge")
    fun submitSignedChallenge(
        @Body body: SignedChallengeRequest
    ): Call<SignedChallengeResponse>

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @POST("/value/retrieveToken")
    fun retrieveToken(
        @Body body: RetrieveTokenRequest
    ): Call<RetrieveTokenResponse>
}