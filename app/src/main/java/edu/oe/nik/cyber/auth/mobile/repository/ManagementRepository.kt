package edu.oe.nik.cyber.auth.mobile.repository

import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.network.management.ManagementApi
import edu.oe.nik.cyber.auth.mobile.network.management.data.ChangePreferredAuthTypeRequest
import edu.oe.nik.cyber.auth.mobile.network.management.data.ChangePreferredAuthTypeResponse
import edu.oe.nik.cyber.auth.mobile.network.management.data.DeleteUserRequest
import edu.oe.nik.cyber.auth.mobile.network.management.data.DeleteUserResponse
import edu.oe.nik.cyber.auth.mobile.storage.PreferredAuthenticationType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ManagementRepository @Inject constructor(
    private val managementApi: ManagementApi
) {

    val deleteUserResult = SingleLiveData<DeleteUserResponse>()

    val changePreferredAuthTypeResult = SingleLiveData<ChangePreferredAuthTypeResponse>()

    fun deleteUser(token: String) {
        managementApi.deleteUser(DeleteUserRequest(token)).enqueue(object: Callback<DeleteUserResponse>{
            override fun onResponse(
                call: Call<DeleteUserResponse>,
                response: Response<DeleteUserResponse>
            ) {
                deleteUserResult.postValue(response.body())
            }

            override fun onFailure(call: Call<DeleteUserResponse>, t: Throwable) {
                deleteUserResult.postValue(DeleteUserResponse("", NETWORK_ERROR))
            }

        })
    }

    fun changePreferredAuthType(token :String, preferredAuthType: PreferredAuthenticationType) {
        managementApi
            .changePreferredAuthType(ChangePreferredAuthTypeRequest(token, preferredAuthType.toString()))
            .enqueue(object: Callback<ChangePreferredAuthTypeResponse> {
                override fun onResponse(
                    call: Call<ChangePreferredAuthTypeResponse>,
                    response: Response<ChangePreferredAuthTypeResponse>
                ) {
                    changePreferredAuthTypeResult.postValue(response.body())
                }

                override fun onFailure(call: Call<ChangePreferredAuthTypeResponse>, t: Throwable) {
                    changePreferredAuthTypeResult.postValue(ChangePreferredAuthTypeResponse("", NETWORK_ERROR))
                }

            })
    }

    companion object {
        const val NETWORK_ERROR = "NETWORK_ERROR"
    }
}