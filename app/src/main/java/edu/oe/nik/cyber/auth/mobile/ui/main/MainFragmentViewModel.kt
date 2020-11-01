package edu.oe.nik.cyber.auth.mobile.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.network.registration.RegistrationApi
import edu.oe.nik.cyber.auth.mobile.network.registration.data.InitiateRegistrationResponse
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import javax.inject.Inject

class MainFragmentViewModel @Inject constructor() : ViewModel(){

    @Inject
    lateinit var registrationApi: RegistrationApi

    @Inject
    lateinit var credentialStorage: CredentialStorage

    val hello: String = "Hello from the view model, again"

    fun updateHello() {
        Log.d("TAG", credentialStorage.jwt)
        credentialStorage.jwt = "let's test it"
        Log.d("TAG", credentialStorage.jwt)
    }


    fun startRegistration() {
        val call = registrationApi.startRegistration("yolo")
    }

}