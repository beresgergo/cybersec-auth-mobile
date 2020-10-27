package edu.oe.nik.cyber.auth.mobile.network.registration

import edu.oe.nik.cyber.auth.mobile.network.NetworkConstants
import edu.oe.nik.cyber.auth.mobile.network.registration.data.*
import retrofit2.Call
import retrofit2.http.*

interface RegistrationApi {

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @GET("/user/{username}")
    fun startRegistration(
        @Path("username") username: String
    ) : InitiateRegistrationResponse

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @POST("/user/{username}/totpSecret")
    fun submitTotpSecret(
        @Path("username") username: String,
        @Body body: SubmitTotpTokenRequest
    ) : Call<SubmitTotpTokenResponse>

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @POST("/user/{username}/publicKey")
    fun submitPublicKey(
        @Path("username") username: String,
        @Body body : SubmitPublicKeyRequest
    ) : Call<SubmitPublicKeyResponse>

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @POST("/user/{username}/finalize")
    fun finalizeRegistration(
        @Path("username") username: String,
        @Body body : PreferredAuthTypeRequest
    ) : Call<PreferredAuthTypeResponse>
}