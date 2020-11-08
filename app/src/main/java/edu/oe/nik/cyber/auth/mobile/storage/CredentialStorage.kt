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
    var jwt: String
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

    var preferredAuthType: String
        get() = insecureSharedPreferences.getString(PREFERRED_AUTH_TYPE, "")
        set(value) {
            with(insecureSharedPreferences.edit()) {
                putString(PREFERRED_AUTH_TYPE, value)
                apply()
            }
            with(secureSharedPreferences.edit()) {
                putString(PREFERRED_AUTH_TYPE, value)
                apply()
            }
        }
}
