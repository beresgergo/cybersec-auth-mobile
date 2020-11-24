package edu.oe.nik.cyber.auth.mobile.ui.authenticated

import androidx.lifecycle.ViewModel
import edu.oe.nik.cyber.auth.mobile.SingleLiveData
import edu.oe.nik.cyber.auth.mobile.repository.ManagementRepository
import edu.oe.nik.cyber.auth.mobile.storage.CredentialStorage
import edu.oe.nik.cyber.auth.mobile.storage.PreferredAuthenticationType
import javax.inject.Inject

class AuthenticatedViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var managementRepository: ManagementRepository

    @Inject
    lateinit var credentialStorage: CredentialStorage

    lateinit var selection: PreferredAuthenticationType

    val shouldRestart = SingleLiveData<Boolean>()

    fun presetSelection() {
        selection = credentialStorage.preferredAuthType
    }

    fun logout() {
        credentialStorage.clearSessionInfo()
        shouldRestart.postValue(true)
    }

    fun changeAuthenticationType() {
        credentialStorage.jwt?.let {
            managementRepository.changePreferredAuthType(it, selection) { credentialStorage.preferredAuthType = selection }
        }
    }

    fun deleteUser() {
        credentialStorage.jwt?.let {
            managementRepository.deleteUser(it) { credentialStorage.clearStorage() }
        }
    }
}