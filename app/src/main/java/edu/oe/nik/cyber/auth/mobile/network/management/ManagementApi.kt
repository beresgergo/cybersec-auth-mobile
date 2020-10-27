package edu.oe.nik.cyber.auth.mobile.network.management

import edu.oe.nik.cyber.auth.mobile.network.NetworkConstants
import edu.oe.nik.cyber.auth.mobile.network.management.data.ChangePreferredAuthTypeRequest
import edu.oe.nik.cyber.auth.mobile.network.management.data.ChangePreferredAuthTypeResponse
import edu.oe.nik.cyber.auth.mobile.network.management.data.DeleteUserRequest
import edu.oe.nik.cyber.auth.mobile.network.management.data.DeleteUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Headers
import retrofit2.http.POST

interface ManagementApi {

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @POST("/management/preferredAuthType")
    fun changePreferredAuthType(
        @Body body: ChangePreferredAuthTypeRequest
    ): Call<ChangePreferredAuthTypeResponse>

    @Headers(NetworkConstants.CONTENT_TYPE_JSON)
    @DELETE("/management/user")
    fun deleteUser(
        @Body body: DeleteUserRequest
    ): Call<DeleteUserResponse>
}