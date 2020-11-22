package edu.oe.nik.cyber.auth.mobile.ui.login.rsa

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.network.login.LoginApi
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import javax.inject.Inject

class RsaLoginViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var loginApi: LoginApi

    @Inject
    lateinit var credentialStorage: CredentialStorage
}