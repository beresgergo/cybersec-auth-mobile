package edu.oe.nik.cyber.auth.mobile.storage

import android.content.SharedPreferences
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CredentialStorageTest {

    @Mock
    private lateinit var mockedInsecureSharedPreferences: SharedPreferences

    @Mock
    private lateinit var mockedSecureSharedPreferences: SharedPreferences

    @Mock
    private lateinit var mockedSharedPreferencesEditor: SharedPreferences.Editor

    private lateinit var sut: CredentialStorage

    @Test
    fun usernameShouldBeStoredInBothPreferences() {
        `when`(mockedInsecureSharedPreferences.edit()).thenReturn(mockedSharedPreferencesEditor)
        `when`(mockedSecureSharedPreferences.edit()).thenReturn(mockedSharedPreferencesEditor)
        `when`(mockedSharedPreferencesEditor
            .putString(any(String::class.java), any(String::class.java))
        ).thenReturn(mockedSharedPreferencesEditor)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        sut.username = "input"

        verify(mockedSharedPreferencesEditor, times(2)).putString(any(String::class.java), any(String::class.java))
        verify(mockedSharedPreferencesEditor, times(2)).apply()
        verify(mockedInsecureSharedPreferences, times(1)).edit()
        verify(mockedSecureSharedPreferences, times(1)).edit()
    }

    @Test
    fun usernameShouldBeRetrievedOnlyFromTheInsecureStorage() {
        val result = "insecureResult"
        `when`(mockedInsecureSharedPreferences.getString(
            eq(CredentialStorage.USERNAME), any(String::class.java)
        )).thenReturn(result)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        assertTrue(sut.username === result)
        verify(mockedSecureSharedPreferences, times(0))
            .getString(any(String::class.java), any(String::class.java))
    }

    @Test
    fun jwtShouldBeStoredInBothPreferences() {
        `when`(mockedInsecureSharedPreferences.edit()).thenReturn(mockedSharedPreferencesEditor)
        `when`(mockedSecureSharedPreferences.edit()).thenReturn(mockedSharedPreferencesEditor)
        `when`(mockedSharedPreferencesEditor
            .putString(any(String::class.java), any(String::class.java))
            ).thenReturn(mockedSharedPreferencesEditor)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        sut.jwt = "input"

        verify(mockedSharedPreferencesEditor, times(2)).putString(any(String::class.java), any(String::class.java))
        verify(mockedSharedPreferencesEditor, times(2)).apply()
        verify(mockedInsecureSharedPreferences, times(1)).edit()
        verify(mockedSecureSharedPreferences, times(1)).edit()
    }

    @Test
    fun jwtShouldBeRetrievedOnlyFromTheInsecureStorage() {
        val result = "insecureResult"
        `when`(mockedInsecureSharedPreferences.getString(
            eq(CredentialStorage.JWT_KEY), any(String::class.java)
        )).thenReturn(result)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        assertTrue(sut.jwt === result)
        verify(mockedSecureSharedPreferences, times(0))
            .getString(any(String::class.java), any(String::class.java))
    }

    @Test
    fun totpSecretTypeShouldBeStoredInBothPreferences() {
        `when`(mockedInsecureSharedPreferences.edit()).thenReturn(mockedSharedPreferencesEditor)
        `when`(mockedSecureSharedPreferences.edit()).thenReturn(mockedSharedPreferencesEditor)
        `when`(mockedSharedPreferencesEditor
            .putString(any(String::class.java), any(String::class.java))
        ).thenReturn(mockedSharedPreferencesEditor)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        sut.totpSecret = "input"

        verify(mockedSharedPreferencesEditor, times(2)).putString(any(String::class.java), any(String::class.java))
        verify(mockedSharedPreferencesEditor, times(2)).apply()
        verify(mockedInsecureSharedPreferences, times(1)).edit()
        verify(mockedSecureSharedPreferences, times(1)).edit()
    }

    @Test
    fun totpSecretShouldBeRetrievedOnlyFromTheInsecureStorage() {
        val result = "insecureResult"
        `when`(mockedInsecureSharedPreferences.getString(
            eq(CredentialStorage.TOTP_SECRET), any(String::class.java)
        )).thenReturn(result)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        assertTrue(sut.totpSecret === result)
        verify(mockedSecureSharedPreferences, times(0))
            .getString(any(String::class.java), any(String::class.java))
    }

    @Test
    fun preferredAuthTypeShouldBeStoredInBothPreferences() {
        `when`(mockedInsecureSharedPreferences.edit()).thenReturn(mockedSharedPreferencesEditor)
        `when`(mockedSecureSharedPreferences.edit()).thenReturn(mockedSharedPreferencesEditor)
        `when`(mockedSharedPreferencesEditor
            .putString(any(String::class.java), any(String::class.java))
        ).thenReturn(mockedSharedPreferencesEditor)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        sut.preferredAuthType = PreferredAuthenticationType.MFA

        verify(mockedSharedPreferencesEditor, times(2)).putString(any(String::class.java), any(String::class.java))
        verify(mockedSharedPreferencesEditor, times(2)).apply()
        verify(mockedInsecureSharedPreferences, times(1)).edit()
        verify(mockedSecureSharedPreferences, times(1)).edit()
    }

    @Test
    fun preferredAuthTypeShouldBeRetrievedOnlyFromTheInsecureStorage() {
        val result = PreferredAuthenticationType.MFA.toString()
        `when`(mockedInsecureSharedPreferences.getString(
            eq(CredentialStorage.PREFERRED_AUTH_TYPE), any(String::class.java)
        )).thenReturn(result)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        assertTrue(sut.preferredAuthType == PreferredAuthenticationType.MFA)
        verify(mockedSecureSharedPreferences, times(0))
            .getString(any(String::class.java), any(String::class.java))
    }

    @Test
    fun clearStorageShouldWipeDataFromBothStorage() {
        `when`(mockedInsecureSharedPreferences.edit()).thenReturn(mockedSharedPreferencesEditor)
        `when`(mockedSecureSharedPreferences.edit()).thenReturn(mockedSharedPreferencesEditor)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        sut.clearStorage()

        verify(mockedSharedPreferencesEditor, times(2)).apply()
        verify(mockedSharedPreferencesEditor, times(2)).clear()
    }

    @Test
    fun hasStoredCredential_shouldBeTrue_whenHasFinalizedIsTrue() {
        `when`(mockedInsecureSharedPreferences.getBoolean(
            eq(CredentialStorage.CREDENTIAL_FINALIZED), anyBoolean())
        ).thenReturn(true)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        assertTrue(sut.hasStoredCredential())
    }

    @Test
    fun hasStoredCredential_shouldBeFalse_whenHasFinalizedIsFalse() {
        `when`(mockedInsecureSharedPreferences.getBoolean(
            eq(CredentialStorage.CREDENTIAL_FINALIZED), anyBoolean())
        ).thenReturn(false)

        sut = CredentialStorage(mockedInsecureSharedPreferences, mockedSecureSharedPreferences)
        assertFalse(sut.hasStoredCredential())
    }
}