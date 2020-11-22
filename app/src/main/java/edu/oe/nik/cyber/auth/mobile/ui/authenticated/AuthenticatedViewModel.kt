package edu.oe.nik.cyber.auth.mobile.ui.authenticated

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.network.management.ManagementApi
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import javax.inject.Inject

class AuthenticatedViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var managementApi: ManagementApi

    @Inject
    lateinit var credentialStorage: CredentialStorage
}