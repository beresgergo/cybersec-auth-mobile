package edu.oe.nik.cyber.auth.mobile.storage

import android.content.SharedPreferences
import javax.inject.Inject

class CredentialStorage @Inject constructor(
    val insecureSharedPreferences: SharedPreferences,
    val secureSharedPreferences: SharedPreferences
){

    companion object SharedPrefKeys {
        const val USERNAME = "username"
        const val JWT_KEY = "jwt"
        const val TOTP_SECRET = "totpSecret"
        const val PREFERRED_AUTH_TYPE = "preferredAuthType"
        const val SESSION_ID = "sessionId"
        const val CREDENTIAL_FINALIZED = "credentialFinalized"
    }

    var username: String
        get() = insecureSharedPreferences.getString(USERNAME, "")
        set(value) {
            with(insecureSharedPreferences.edit()) {
                putString(USERNAME, value)
                apply()
            }
            with(secureSharedPreferences.edit()) {
                putString(USERNAME, value)
                apply()
            }
        }
    var jwt: String?
        get() = insecureSharedPreferences.getString(JWT_KEY, "")
        set(value) {
            with(insecureSharedPreferences.edit()) {
                putString(JWT_KEY, value)
                apply()
            }
            with(secureSharedPreferences.edit()) {
                putString(JWT_KEY, value)
                apply()
            }
        }

    var totpSecret: String
        get() = insecureSharedPreferences.getString(TOTP_SECRET, "")
        set(value) {
            with(insecureSharedPreferences.edit()) {
                putString(TOTP_SECRET, value)
                apply()
            }
            with(secureSharedPreferences.edit()) {
                putString(TOTP_SECRET, value)
                apply()
            }
        }

    var preferredAuthType: PreferredAuthenticationType
        get() = PreferredAuthenticationType.valueOf(insecureSharedPreferences.getString(PREFERRED_AUTH_TYPE, ""))
        set(value) {
            with(insecureSharedPreferences.edit()) {
                putString(PREFERRED_AUTH_TYPE, value.toString())
                apply()
            }
            with(secureSharedPreferences.edit()) {
                putString(PREFERRED_AUTH_TYPE, value.toString())
                apply()
            }
        }

    var sessionId: String?
        get() = insecureSharedPreferences.getString(SESSION_ID, "")
        set(value) {
            with(insecureSharedPreferences.edit()) {
                putString(SESSION_ID, value)
                apply()
            }
            with(secureSharedPreferences.edit()) {
                putString(SESSION_ID, value)
                apply()
            }
        }

    var credentialFinalized: Boolean
        get() = insecureSharedPreferences.getBoolean(CREDENTIAL_FINALIZED, false)
        set(value) {
            with(insecureSharedPreferences.edit()) {
                putBoolean(CREDENTIAL_FINALIZED, value)
                apply()
            }
            with(secureSharedPreferences.edit()) {
                putBoolean(CREDENTIAL_FINALIZED, value)
                apply()
            }
        }

    fun clearStorage() {
        with(insecureSharedPreferences.edit()) {
            clear()
            apply()
        }

        with(secureSharedPreferences.edit()) {
            clear()
            apply()
        }
    }

    fun hasStoredCredential(): Boolean {
        return credentialFinalized
    }

    fun clearSessionInfo() {
        sessionId = ""
        jwt = ""
    }
}
