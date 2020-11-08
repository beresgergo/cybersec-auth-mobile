package edu.oe.nik.cyber.auth.mobile.ui.login.totp

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import javax.inject.Inject

class TotpLoginViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var credentialStorage: CredentialStorage
}